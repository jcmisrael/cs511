-module(mapGTree).
-compile(export_all).
-export([mapGTree/2, plus/1]).

mapGTree(Func, {node, Number, []}) ->
    {node, Func(Number), []};
mapGTree(Func, {node, Number, List}) ->
    {node, Func(Number), mapGTree(Func, List)};
mapGTree(Func, [Head | Tail]) ->
    [mapGTree(Func, Head) | mapGTree(Func, Tail)];
mapGTree(_, []) ->
    [].


