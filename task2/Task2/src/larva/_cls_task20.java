package larva;


import java.util.LinkedHashMap;
import java.io.PrintWriter;

public class _cls_task20 implements _callable{

public static PrintWriter pw; 
public static _cls_task20 root;

public static LinkedHashMap<_cls_task20,_cls_task20> _cls_task20_instances = new LinkedHashMap<_cls_task20,_cls_task20>();
static{
try{
RunningClock.start();
pw = new PrintWriter("C:\\Users\\memos\\workspace\\Task2/src/output_task2.txt");

root = new _cls_task20();
_cls_task20_instances.put(root, root);
  root.initialisation();
}catch(Exception ex)
{ex.printStackTrace();}
}

_cls_task20 parent; //to remain null - this class does not have a parent!
int no_automata = 1;
 public boolean isLoggedIn =false ;
 public int alerts =0 ;
 public boolean viewed =false ;

public static void initialize(){}
//inheritance could not be used because of the automatic call to super()
//when the constructor is called...we need to keep the SAME parent if this exists!

public _cls_task20() {
}

public void initialisation() {
}

public static _cls_task20 _get_cls_task20_inst() { synchronized(_cls_task20_instances){
 return root;
}
}

public boolean equals(Object o) {
 if ((o instanceof _cls_task20))
{return true;}
else
{return false;}
}

public int hashCode() {
return 0;
}

public void _call(String _info, int... _event){
synchronized(_cls_task20_instances){
_performLogic_badActionsProperty(_info, _event);
}
}

public void _call_all_filtered(String _info, int... _event){
}

public static void _call_all(String _info, int... _event){

_cls_task20[] a = new _cls_task20[1];
synchronized(_cls_task20_instances){
a = _cls_task20_instances.keySet().toArray(a);}
for (_cls_task20 _inst : a)

if (_inst != null) _inst._call(_info, _event);
}

public void _killThis(){
try{
if (--no_automata == 0){
synchronized(_cls_task20_instances){
_cls_task20_instances.remove(this);}
}
else if (no_automata < 0)
{throw new Exception("no_automata < 0!!");}
}catch(Exception ex){ex.printStackTrace();}
}

int _state_id_badActionsProperty = 3;

public void _performLogic_badActionsProperty(String _info, int... _event) {

_cls_task20.pw.println("[badActionsProperty]AUTOMATON::> badActionsProperty("+") STATE::>"+ _string_badActionsProperty(_state_id_badActionsProperty, 0));
_cls_task20.pw.flush();

if (0==1){}
else if (_state_id_badActionsProperty==3){
		if (1==0){}
		else if ((_occurredEvent(_event,0/*logIn*/)) && (isLoggedIn ==false &&alerts <6 )){
		isLoggedIn =true ;

		_state_id_badActionsProperty = 1;//moving to state inSystem
		_goto_badActionsProperty(_info);
		}
		else if ((_occurredEvent(_event,0/*logIn*/)) && (isLoggedIn ==false &&alerts >5 )){
		isLoggedIn =true ;

		_state_id_badActionsProperty = 2;//moving to state moreThanFiveAlerts
		_goto_badActionsProperty(_info);
		}
}
else if (_state_id_badActionsProperty==2){
		if (1==0){}
		else if ((_occurredEvent(_event,4/*addAlert*/))){
		_cls_task20.pw .println ("Only latest 5 alerts will show up");

		_state_id_badActionsProperty = 2;//moving to state moreThanFiveAlerts
		_goto_badActionsProperty(_info);
		}
		else if ((_occurredEvent(_event,8/*viewAlerts*/)) && (viewed ==false )){
		viewed =true ;

		_state_id_badActionsProperty = 2;//moving to state moreThanFiveAlerts
		_goto_badActionsProperty(_info);
		}
		else if ((_occurredEvent(_event,6/*deleteAlerts*/))){
		alerts =0 ;

		_state_id_badActionsProperty = 1;//moving to state inSystem
		_goto_badActionsProperty(_info);
		}
		else if ((_occurredEvent(_event,8/*viewAlerts*/)) && (viewed ==true )){
		_cls_task20.pw .println ("BAD STATE (ALREADY VIEWED)");

		_state_id_badActionsProperty = 0;//moving to state cannotDeleteOrAlreadyViewed
		_goto_badActionsProperty(_info);
		}
		else if ((_occurredEvent(_event,2/*logOut*/)) && (isLoggedIn ==true )){
		isLoggedIn =false ;

		_state_id_badActionsProperty = 3;//moving to state loggedOut
		_goto_badActionsProperty(_info);
		}
}
else if (_state_id_badActionsProperty==1){
		if (1==0){}
		else if ((_occurredEvent(_event,2/*logOut*/)) && (isLoggedIn ==true )){
		isLoggedIn =false ;

		_state_id_badActionsProperty = 3;//moving to state loggedOut
		_goto_badActionsProperty(_info);
		}
		else if ((_occurredEvent(_event,4/*addAlert*/))){
		alerts ++;

		_state_id_badActionsProperty = 1;//moving to state inSystem
		_goto_badActionsProperty(_info);
		}
		else if ((_occurredEvent(_event,6/*deleteAlerts*/)) && (alerts !=0 )){
		alerts =0 ;

		_state_id_badActionsProperty = 1;//moving to state inSystem
		_goto_badActionsProperty(_info);
		}
		else if ((_occurredEvent(_event,8/*viewAlerts*/)) && (viewed ==false )){
		viewed =true ;

		_state_id_badActionsProperty = 1;//moving to state inSystem
		_goto_badActionsProperty(_info);
		}
		else if ((_occurredEvent(_event,4/*addAlert*/)) && (alerts ==5 )){
		alerts ++;

		_state_id_badActionsProperty = 2;//moving to state moreThanFiveAlerts
		_goto_badActionsProperty(_info);
		}
		else if ((_occurredEvent(_event,6/*deleteAlerts*/)) && (alerts ==0 )){
		_cls_task20.pw .println ("BAD STATE (CANNOT DELETE)");

		_state_id_badActionsProperty = 0;//moving to state cannotDeleteOrAlreadyViewed
		_goto_badActionsProperty(_info);
		}
		else if ((_occurredEvent(_event,8/*viewAlerts*/)) && (viewed ==true )){
		_cls_task20.pw .println ("BAD STATE (ALREADY VIEWED)");

		_state_id_badActionsProperty = 0;//moving to state cannotDeleteOrAlreadyViewed
		_goto_badActionsProperty(_info);
		}
}
}

public void _goto_badActionsProperty(String _info){
_cls_task20.pw.println("[badActionsProperty]MOVED ON METHODCALL: "+ _info +" TO STATE::> " + _string_badActionsProperty(_state_id_badActionsProperty, 1));
_cls_task20.pw.flush();
}

public String _string_badActionsProperty(int _state_id, int _mode){
switch(_state_id){
case 3: if (_mode == 0) return "loggedOut"; else return "loggedOut";
case 0: if (_mode == 0) return "cannotDeleteOrAlreadyViewed"; else return "!!!SYSTEM REACHED BAD STATE!!! cannotDeleteOrAlreadyViewed "+new _BadStateExceptiontask2().toString()+" ";
case 2: if (_mode == 0) return "moreThanFiveAlerts"; else return "moreThanFiveAlerts";
case 1: if (_mode == 0) return "inSystem"; else return "inSystem";
default: return "!!!SYSTEM REACHED AN UNKNOWN STATE!!!";
}
}

public boolean _occurredEvent(int[] _events, int event){
for (int i:_events) if (i == event) return true;
return false;
}
}