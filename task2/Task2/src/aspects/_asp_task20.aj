package aspects;

import larva.*;
public aspect _asp_task20 {

public static Object lock = new Object();

boolean initialized = false;

after():(staticinitialization(*)){
if (!initialized){
	initialized = true;
	_cls_task20.initialize();
}
}
before () : (call(* *.logIn(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_task20.lock){

_cls_task20 _cls_inst = _cls_task20._get_cls_task20_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 0/*logIn*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 0/*logIn*/);
}
}
before () : (call(* *.viewAlerts(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_task20.lock){

_cls_task20 _cls_inst = _cls_task20._get_cls_task20_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 8/*viewAlerts*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 8/*viewAlerts*/);
}
}
before () : (call(* *.addAlert(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_task20.lock){

_cls_task20 _cls_inst = _cls_task20._get_cls_task20_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 4/*addAlert*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 4/*addAlert*/);
}
}
before () : (call(* *.deleteAlerts(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_task20.lock){

_cls_task20 _cls_inst = _cls_task20._get_cls_task20_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 6/*deleteAlerts*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 6/*deleteAlerts*/);
}
}
before () : (call(* *.logOut(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_task20.lock){

_cls_task20 _cls_inst = _cls_task20._get_cls_task20_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 2/*logOut*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 2/*logOut*/);
}
}
}