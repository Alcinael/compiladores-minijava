package canon;

class MoveCall extends tree.stm.Stm {
  tree.exp.TEMP dst;
  tree.exp.CALL src;
  MoveCall(tree.exp.TEMP d, tree.exp.CALL s) {dst=d; src=s;}
  public tree.exp.ExpList kids() {return src.kids();}
  public tree.stm.Stm build(tree.exp.ExpList kids) {
	return new tree.stm.MOVE(dst, src.build(kids));
  }
}   
  
class ExpCall extends tree.stm.Stm {
  tree.exp.CALL call;
  ExpCall(tree.exp.CALL c) {call=c;}
  public tree.exp.ExpList kids() {return call.kids();}
  public tree.stm.Stm build(tree.exp.ExpList kids) {
	return new tree.exp.EXP(call.build(kids));
  }
}   
  
class StmExpList {
  tree.stm.Stm stm;
  tree.exp.ExpList exps;
  StmExpList(tree.stm.Stm s, tree.exp.ExpList e) {stm=s; exps=e;}
}

public class Canon {
  
 static boolean isNop(tree.stm.Stm a) {
   return a instanceof tree.exp.EXP
          && ((tree.exp.EXP)a).exp instanceof tree.exp.CONST;
 }

 static tree.stm.Stm seq(tree.stm.Stm a, tree.stm.Stm b) {
    if (isNop(a)) return b;
    else if (isNop(b)) return a;
    else return new tree.stm.SEQ(a,b);
 }

 static boolean commute(tree.stm.Stm a, tree.exp.Exp b) {
    return isNop(a)
        || b instanceof tree.exp.NAME
        || b instanceof tree.exp.CONST;
 }

 static tree.stm.Stm do_stm(tree.stm.SEQ s) { 
	return seq(do_stm(s.left), do_stm(s.right));
 }

 static tree.stm.Stm do_stm(tree.stm.MOVE s) { 
	if (s.dst instanceof tree.exp.TEMP 
	     && s.src instanceof tree.exp.CALL) 
		return reorder_stm(new MoveCall((tree.exp.TEMP)s.dst,
						(tree.exp.CALL)s.src));
	else if (s.dst instanceof tree.exp.ESEQ)
	    return do_stm(new tree.stm.SEQ(((tree.exp.ESEQ)s.dst).stm,
					new tree.stm.MOVE(((tree.exp.ESEQ)s.dst).exp,
						  s.src)));
	else return reorder_stm(s);
 }

 static tree.stm.Stm do_stm(tree.stm.EXP s) { 
	if (s.exp instanceof tree.exp.CALL)
	       return reorder_stm(new ExpCall((tree.exp.CALL)s.exp));
	else return reorder_stm(s);
 } 

 static tree.stm.Stm do_stm(tree.stm.Stm s) {
     if (s instanceof tree.stm.SEQ) return do_stm((tree.stm.SEQ)s);
     else if (s instanceof tree.stm.MOVE) return do_stm((tree.stm.MOVE)s);
     else if (s instanceof tree.stm.EXP) return do_stm((tree.stm.EXP)s);
     else return reorder_stm(s);
 }

 static tree.stm.Stm reorder_stm(tree.stm.Stm s) {
     StmExpList x = reorder(s.kids());
     return seq(x.stm, s.build(x.exps));
 }

 static tree.exp.ESEQ do_exp(tree.exp.ESEQ e) {
      tree.stm.Stm stms = do_stm(e.stm);
      tree.exp.ESEQ b = do_exp(e.exp);
      return new tree.exp.ESEQ(seq(stms,b.stm), b.exp);
  }

 static tree.exp.ESEQ do_exp (tree.exp.Exp e) {
       if (e instanceof tree.exp.ESEQ) return do_exp((tree.exp.ESEQ)e);
       else return reorder_exp(e);
 }
         
 static tree.exp.ESEQ reorder_exp (tree.exp.Exp e) {
     StmExpList x = reorder(e.kids());
     return new tree.exp.ESEQ(x.stm, e.build(x.exps));
 }

 static StmExpList nopNull = new StmExpList(new tree.exp.EXP(new tree.exp.CONST(0)),null);

 static StmExpList reorder(tree.ExpList exps) {
     if (exps==null) return nopNull;
     else {
       tree.exp.Exp a = exps.head;
       if (a instanceof tree.exp.CALL) {
         Temp.Temp t = new Temp.Temp();
	 tree.exp.Exp e = new tree.exp.ESEQ(new tree.stm.MOVE(new tree.exp.TEMP(t), a),
				    new tree.exp.TEMP(t));
         return reorder(new tree.ExpList(e, exps.tail));
       } else {
	 tree.exp.ESEQ aa = do_exp(a);
	 StmExpList bb = reorder(exps.tail);
	 if (commute(bb.stm, aa.exp))
	      return new StmExpList(seq(aa.stm,bb.stm), 
				    new tree.ExpList(aa.exp,bb.exps));
	 else {
	   Temp.Temp t = new Temp.Temp();
	   return new StmExpList(
			  seq(aa.stm, 
			    seq(new tree.stm.MOVE(new tree.exp.TEMP(t),aa.exp),
				 bb.stm)),
			  new tree.ExpList(new tree.exp.TEMP(t), bb.exps));
	 }
       }
     }
 }
        
 static tree.StmList linear(tree.stm.SEQ s, tree.stm.StmList l) {
      return linear(s.left,linear(s.right,l));
 }
 static tree.stm.StmList linear(tree.stm.Stm s, tree.stm.StmList l) {
    if (s instanceof tree.stm.SEQ) return linear((tree.stm.SEQ)s, l);
    else return new tree.stm.StmList(s,l);
 }

 static public tree.stm.StmList linearize(tree.stm.Stm s) {
    return linear(do_stm(s), null);
 }
}