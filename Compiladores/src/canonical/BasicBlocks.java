package canon;

import temp.label;
import tree.stm.*;

public class BasicBlocks {
  public StmListList blocks;
  public temp.Label done;

  private StmListList lastBlock;
  private tree.stm.StmList lastStm;

  private void addStm(tree.stm.Stm s) {
	lastStm = lastStm.tail = new tree.stm.StmList(s,null);
  }

  private void doStms(tree.stm.StmList l) {
      if (l==null) 
	doStms(new tree.stm.StmList(new tree.stm.JUMP(done), null));
      else if (l.head instanceof tree.stm.JUMP 
	      || l.head instanceof tree.stm.CJUMP) {
	addStm(l.head);
	mkBlocks(l.tail);
      } 
      else if (l.head instanceof tree.stm.LABEL)
           doStms(new tree.stm.StmList(new tree.stm.JUMP(((tree.stm.LABEL)l.head).label), 
	  			   l));
      else {
	addStm(l.head);
	doStms(l.tail);
      }
  }

  void mkBlocks(tree.stm.StmList l) {
     if (l==null) return;
     else if (l.head instanceof tree.stm.LABEL) {
	lastStm = new tree.stm.StmList(l.head,null);
        if (lastBlock==null)
  	   lastBlock= blocks= new StmListList(lastStm,null);
        else
  	   lastBlock = lastBlock.tail = new StmListList(lastStm,null);
	doStms(l.tail);
     }
     else mkBlocks(new tree.stm.StmList(new tree.stm.LABEL(new temp.Label()), l));
  }
   

  public BasicBlocks(tree.stm.StmList stms) {
    done = new temp.Label();
    mkBlocks(stms);
  }
}