package com.uom.cps3230;

import com.uom.cps3230.enums.SystemStates;
import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;
import nz.ac.waikato.modeljunit.GreedyTester;
import nz.ac.waikato.modeljunit.StopOnFailureListener;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionPairCoverage;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class ModelTest implements FsmModel {
    private WebsiteAPI systemUnderTest = new WebsiteAPI();

    private SystemStates modelWebsiteAPI = SystemStates.LOGGED_OUT;
    private boolean fiveOrLessAlerts = false;
    private boolean moreThanFiveAlerts = false;
    private int alerts = 0;

    public SystemStates getState() {
        return modelWebsiteAPI;
    }

    public void reset(final boolean var1) {
        if (var1) {
            systemUnderTest = new WebsiteAPI();
        }
        modelWebsiteAPI = SystemStates.LOGGED_OUT;
        fiveOrLessAlerts = false;
        moreThanFiveAlerts = false;
        alerts = 0;
    }

    public boolean logInSiteGuard() {
        return getState().equals(SystemStates.LOGGED_OUT);
    }

    public @Action void logInSite() {
        systemUnderTest.logIn();
        alerts = systemUnderTest.getAlerts();

        if (alerts > 5){
            modelWebsiteAPI = SystemStates.MORE_THAN_FIVE_ALERTS;
            fiveOrLessAlerts = false;
            moreThanFiveAlerts = true;

            assertEquals(moreThanFiveAlerts, systemUnderTest.isMoreThanFiveAlerts());
        } else {
            modelWebsiteAPI = SystemStates.FIVE_OR_LESS_ALERTS;
            fiveOrLessAlerts = true;
            moreThanFiveAlerts = false;

            assertEquals(fiveOrLessAlerts, systemUnderTest.isFiveOrLessAlerts());
        }
    }

    public boolean logOutSiteGuard() {
        return !getState().equals(SystemStates.LOGGED_OUT);
    }

    public @Action void logOutSite() {
        systemUnderTest.logOut();
        alerts = systemUnderTest.getAlerts();

        modelWebsiteAPI = SystemStates.LOGGED_OUT;
        fiveOrLessAlerts = false;
        moreThanFiveAlerts = false;

        assertEquals(fiveOrLessAlerts || moreThanFiveAlerts, systemUnderTest.isLoggedIn());
    }

    public boolean deleteAlertsSiteGuard() {
        return getState().equals(SystemStates.FIVE_OR_LESS_ALERTS) || getState().equals(SystemStates.MORE_THAN_FIVE_ALERTS);
    }

    public @Action void deleteAlertsSite() {
        systemUnderTest.deleteAlerts();
        alerts = systemUnderTest.getAlerts();

        if (getState().equals(SystemStates.FIVE_OR_LESS_ALERTS)) {
            modelWebsiteAPI = SystemStates.FIVE_OR_LESS_ALERTS;
            fiveOrLessAlerts = true;
            moreThanFiveAlerts = false;

            assertEquals(fiveOrLessAlerts, systemUnderTest.isFiveOrLessAlerts());
        } else {
            modelWebsiteAPI = SystemStates.FIVE_OR_LESS_ALERTS;
            fiveOrLessAlerts = true;
            moreThanFiveAlerts = false;

            assertEquals(fiveOrLessAlerts, systemUnderTest.isFiveOrLessAlerts());
        }
    }

    public boolean createAlertsSiteGuard() {
        return getState().equals(SystemStates.FIVE_OR_LESS_ALERTS) || getState().equals(SystemStates.MORE_THAN_FIVE_ALERTS);
    }

    public @Action void createAlertsSite() {
        systemUnderTest.createAlerts();
        alerts = systemUnderTest.getAlerts();

        if (getState().equals(SystemStates.FIVE_OR_LESS_ALERTS)) {
            if (alerts > 5){
                modelWebsiteAPI = SystemStates.MORE_THAN_FIVE_ALERTS;
                fiveOrLessAlerts = false;
                moreThanFiveAlerts = true;

                assertEquals(moreThanFiveAlerts, systemUnderTest.isMoreThanFiveAlerts());
            }else{
                modelWebsiteAPI = SystemStates.FIVE_OR_LESS_ALERTS;
                fiveOrLessAlerts = true;
                moreThanFiveAlerts = false;

                assertEquals(fiveOrLessAlerts, systemUnderTest.isFiveOrLessAlerts());
            }
        } else {
            modelWebsiteAPI = SystemStates.MORE_THAN_FIVE_ALERTS;
            fiveOrLessAlerts = false;
            moreThanFiveAlerts = true;

            assertEquals(moreThanFiveAlerts, systemUnderTest.isMoreThanFiveAlerts());
        }
    }

    //Test runner
    @Test
    public void BulbOperatorModelRunner() {
        final GreedyTester tester = new GreedyTester(new ModelTest());
        tester.setRandom(new Random()); //Allows for a random path each time the model is run.
        tester.buildGraph(); //Builds a model of our FSM to ensure that the coverage metrics are correct.
        tester.addListener(new StopOnFailureListener()); //This listener forces the test class to stop running as soon as a failure is encountered in the model.
        tester.addListener("verbose"); //This gives you printed statements of the transitions being performed along with the source and destination states.
        tester.addCoverageMetric(new TransitionPairCoverage()); //Records the transition pair coverage i.e. the number of paired transitions traversed during the execution of the test.
        tester.addCoverageMetric(new StateCoverage()); //Records the state coverage i.e. the number of states which have been visited during the execution of the test.
        tester.addCoverageMetric(new ActionCoverage()); //Records the number of @Action methods which have ben executed during the execution of the test.
        tester.generate(500); //Generates 500 transitions
        tester.printCoverage(); //Prints the coverage metrics specified above.
    }
}
