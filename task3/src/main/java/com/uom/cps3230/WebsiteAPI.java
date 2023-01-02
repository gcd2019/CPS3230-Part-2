package com.uom.cps3230;

public class WebsiteAPI {
    private boolean fiveOrLessAlerts = false;
    private boolean moreThanFiveAlerts = false;
    private int alerts = 0;

    boolean logIn() {
        if (!(fiveOrLessAlerts || moreThanFiveAlerts)) {
            if (alerts > 5) {
                fiveOrLessAlerts = false;
                moreThanFiveAlerts = true;
            } else {
                fiveOrLessAlerts = true;
                moreThanFiveAlerts = false;
            }
            return true;
        } else {
            throw new IllegalStateException();
        }
    }

    boolean logOut() {
        if (fiveOrLessAlerts || moreThanFiveAlerts) {
            moreThanFiveAlerts = false;
            fiveOrLessAlerts = false;
            return true;
        } else {
            throw new IllegalStateException();
        }
    }

    boolean createAlerts() {
        alerts++;
        if (fiveOrLessAlerts) {
            if (alerts > 5) {
                fiveOrLessAlerts = false;
                moreThanFiveAlerts = true;
            } else {
                moreThanFiveAlerts = false;
            }

        } else if (moreThanFiveAlerts){
            fiveOrLessAlerts = false;
        }else {
            throw new IllegalStateException();
        }

        return true;
    }

    boolean deleteAlerts() {
        alerts = 0;
        if (fiveOrLessAlerts) {
            moreThanFiveAlerts = false;
        } else if (moreThanFiveAlerts) {
            fiveOrLessAlerts = true;
            moreThanFiveAlerts = false;
        } else {
            throw new IllegalStateException();
        }

        return true;
    }

    boolean isFiveOrLessAlerts(){
        return fiveOrLessAlerts;
    }

    boolean isMoreThanFiveAlerts(){
        return moreThanFiveAlerts;
    }

    boolean isLoggedIn(){
        return fiveOrLessAlerts || moreThanFiveAlerts;
    }

    int getAlerts(){
        return alerts;
    }

}
