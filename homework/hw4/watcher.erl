-module(watcher).
-export([new/1]).
-author("Jake Israel").

new(ProcNum) ->
    new(ProcNum, 0, []).

new(ProcNum, StartID, List) when ProcNum < 10 ->
    PID = spawn(fun () -> start(ProcNum, StartID) end),
    ReturnList = List ++ [PID],
    ReturnList;
new(ProcNum, StartID, List) ->
    PID = spawn(fun () -> start(10, StartID) end),
    new(ProcNum - 10, StartID + 10, List ++ [PID]).

start(ProcNum, StartID) ->
    SensorList = spawn_sensors(ProcNum, StartID),
    io:format("Watcher started: ~w~n", [dict:to_list(SensorList)]),
    loop(SensorList).

spawn_sensors(NumSensors, StartID) ->
    spawn_sensors(NumSensors, StartID, dict:new()).
spawn_sensors(0, _StartID, Dict) ->
    Dict;
spawn_sensors(NumSensors, StartID, Dict) ->
    {PID, _Ref} = spawn_monitor(sensor, loop, [self()]),
    spawn_sensors(NumSensors - 1, StartID + 1, dict:store(PID, StartID, Dict)).

loop(SensorList) ->
    receive
        {From, Measurement} ->
            io:format("Sensor ~B: ~B~n", [dict:fetch(From, SensorList), Measurement]);
        {'DOWN', _Ref, process, Pid2, Reason} ->
            SensorID = dict:fetch(Pid2, SensorList),
            io:format("~nSensor ~B DIED! Reason: ~w. Restarting...~n", [SensorID, Reason]),
            Dict2 = dict:erase(Pid2, SensorList),
            NewSensorList = spawn_sensors(1, SensorID, Dict2),
            io:format("Sensors ~B RESTARTED!~n~w~n", [SensorID, dict:to_list(NewSensorList)]),
            loop(NewSensorList)
    end,
    loop(SensorList).
