package telran.util;

import java.util.Iterator;

public class TreeIterator<E> implements Iterator<E> {
NodeTree<E> current;
NodeTree<E> previous;
Tree<E> tree;
public TreeIterator(Tree<E> tree) {
	this.tree=tree;
	current=getLeastNode(tree.root);
}
	private NodeTree<E> getLeastNode(NodeTree<E> root) {
		NodeTree<E> res=null;
		if(root!=null) {
			for( res=root;res.left!=null;res=res.left) {}
		}
		return res;
}
	@Override
	public boolean hasNext() {
		return current!=null;
	}

	@Override
	public E next() {
		E res=current.obj;
		previous=current;
		current=current.right==null?getLeastParent(current):
			getLeastNode(current.right);
		return res;
	}
	private NodeTree<E> getLeastParent(NodeTree<E> node) {
		NodeTree<E>tmp=null;
		for(tmp=node;tmp.parent!=null;tmp=tmp.parent) {
			if(tmp.parent.left==tmp)
				break;
		}
		return tmp.parent;
	}
	@Override
	public void remove() {
		if(tree.isJunction(previous))
			current=previous;
		tree.removeNode(previous);
		
	}

}
