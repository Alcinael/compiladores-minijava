package canon;

public class TraceSchedule {

  public tree.stm.StmList stms;
  BasicBlocks theBlocks;
  java.util.Dictionary table = new java.util.Hashtable();

  tree.stm.StmList getLast(tree.stm.StmList block) {
     tree.stm.StmList l=block;
     while (l.tail.tail!=null)  l=l.tail;
     return l;
  }

  void trace(tree.stm.StmList l) {
   for(;;) {
     tree.stm.LABEL lab = (tree.stm.LABEL)l.head;
     table.remove(lab.label);
     tree.stm.StmList last = getLast(l);
     tree.stm.Stm s = last.tail.head;
     if (s instanceof tree.stm.JUMP) {
	tree.stm.JUMP j = (tree.stm.JUMP)s;
        tree.stm.StmList target = (tree.stm.StmList)table.get(j.targets.head);
	if (j.targets.tail==null && target!=null) {
               last.tail=target;
	       l=target;
        }
	else {
	  last.tail.tail=getNext();
	  return;
        }
     }
     else if (s instanceof tree.stm.CJUMP) {
	tree.stm.CJUMP j = (tree.stm.CJUMP)s;
        tree.stm.StmList t = (tree.stm.StmList)table.get(j.iftrue);
        tree.stm.StmList f = (tree.stm.StmList)table.get(j.iffalse);
        if (f!=null) {
	  last.tail.tail=f; 
	  l=f;
	}
        else if (t!=null) {
	  last.tail.head=new tree.stm.CJUMP(tree.stm.CJUMP.notRel(j.relop),
					j.left,j.right,
					j.iffalse,j.iftrue);
	  last.tail.tail=t;
	  l=t;
        }
        else {
	  Temp.Label ff = new Temp.Label();
	  last.tail.head=new tree.stm.CJUMP(j.relop,j.left,j.right,
					j.iftrue,ff);
	  last.tail.tail=new tree.stm.StmList(new tree.stm.LABEL(ff),
		           new tree.stm.StmList(new tree.stm.JUMP(j.iffalse),
					    getNext()));
	  return;
        }
     }
     else throw new Error("Bad basic block in TraceSchedule");
    }
  }

  tree.stm.StmList getNext() {
      if (theBlocks.blocks==null) 
	return new tree.stm.StmList(new tree.stm.LABEL(theBlocks.done), null);
      else {
	 tree.stm.StmList s = theBlocks.blocks.head;
	 tree.stm.LABEL lab = (tree.stm.LABEL)s.head;
	 if (table.get(lab.label) != null) {
          trace(s);
	  return s;
         }
         else {
	   theBlocks.blocks = theBlocks.blocks.tail;
           return getNext();
         }
      }
  }

  public TraceSchedule(BasicBlocks b) {
    theBlocks=b;
    for(StmListList l = b.blocks; l!=null; l=l.tail)
       table.put(((tree.stm.LABEL)l.head.head).label, l.head);
    stms=getNext();
    table=null;
  }        
}

