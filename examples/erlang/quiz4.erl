-module(quiz4).
-compile(export_all).
-export([sum/1]).
% Jake Israel & George Beckert

sum([List]) ->
    sum(List, 0).
sum([], N) ->
    N;
sum([Head | Tail], N) ->
    sum(Tail, N + Head).

maximum([Head | Tail]) ->
    maximum(Tail, Head).
maximum([], N) ->
    N;
maximum([Head | Tail], N) when Head > N ->
    maximum(Tail, Head);
maximum([_ | Tail], N) ->
    maximum(Tail, N).

zip(List1, List2) ->
    zip(List1, List2, []).
zip([], [], List) ->
    List;
zip([Head1 | Tail1], [Head2 | Tail2], List3) ->
    zip(Tail1, Tail2, List3 ++ [[Head1 , Head2]]).

reverse(List) ->
    reverse(List, []).
reverse([], List) ->
    List;
reverse([Head | Tail], List) ->
    reverse(Tail, [Head] ++ List).

append(List1, [Head | Tail]) ->
    append(List1 ++ [Head], Tail);
append(List1, []) ->
    List1.

evenL(List) ->
    evenL(List, []).
evenL([Head | Tail], Return) when Head rem 2 =:= 0 ->
    evenL(Tail, Return ++ [Head]);
evenL([_ | Tail], Return) ->
    evenL(Tail, Return);
evenL([], Return) ->
    Return.

take(List, N) ->
    take(List, N, []).
take([], _, Return) ->
    Return;
take(_, N, Return) when N =< 0 ->
    Return;
take([Head | Tail], N, Return) ->
    take(Tail, N - 1, Return ++ [Head]).

drop(List, N) when N =< 0 ->
    List;
drop([], _) ->
    [];
drop([_ | Tail], N) ->
    drop(Tail, N - 1).
