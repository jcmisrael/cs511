byte turn = 1;
bool wantP = false;
bool wantQ = false;

active proctype P(){
  do
    ::
       wantP = true;
       do
         :: !wantQ -> break;
         :: else ->
            if
              :: (turn == 1)
              :: (turn == 2) ->
                 wantP = false;
                 (turn == 1);
                 wantP = true;
            fi
            turn = 2;
            wantP = false;
       od;
  od;
}

active proctype Q(){
  do
    ::
       wantQ = true;
       do
         :: !wantP -> break;
         :: else ->
            if
              :: (turn == 2)
              :: (turn == 1) ->
                 wantQ = false;
                 (turn == 2);
                 wantQ = true;
            fi
            turn = 1;
            wantQ = false;
       od;
  od;
}
