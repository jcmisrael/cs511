bool wantP = false, wantQ = false;

active proctype P(){
  do ::
        atomic {
          !wantQ;
          wantP = true;
        }
        wantP = false
  od
}

active proctype Q(){
  do ::
        atomic {
          !wantP;
          wantQ = true
        }
        wantQ = false
  od
}