package commandLine;

import java.util.ArrayList;
import java.util.List;

/* Un arbol con un atributo mas que identifica si es un directorio o un archivo (atributo nodeType)
 * es una modificacion de: https://stackoverflow.com/a/40622616/1005607
*/

public class Inode<T>{

    private T data = null;
    private List<Inode> children = new ArrayList<>();
    private Inode parent = null;
    private char nodeType = 'd';
    private List<T> path = new ArrayList<T>();
    
    public Inode(char t, T data) {
        this.data = data;
        this.nodeType = t;
    }

    public void addChild(Inode child) {
        child.setParent(this);
        this.children.add(child);
    }

    public void addChild(char t, T data) {
        Inode<T> newChild = new Inode<>(t, data);
        this.addChild(newChild);
    }
    
    public boolean ParentExists() {
    	if (this.getParent() != null) {
    		return true;
    	}
    	return false;
    }
    
    /* verifica si el hijo ya existe  */
    
    public boolean ChildExists(T data) {
    	 List<Inode> myChildren = this.getChildren();
    	 for (Inode n: myChildren) {
    		 if (n.getData().equals(data)) {
    			 return true;
    		 }
    	 }
    	 return false;
    }

    public List<Inode> getChildren() {
        return children;
    }

    public T getData() {
        return data;
    }
    
    public char getType() {
        return nodeType;
    }

    public void setData(T data) {
        this.data = data;
    }

    private void setParent(Inode parent) {
        this.parent = parent;
    }

    public Inode getParent() {
        return parent;
    }
    
    public Inode getChild(T child) {
   		List<Inode> myChildren = this.getChildren();
   		for (Inode n: myChildren) {
  			if (n.getData().equals(child)) {
   				return n;
   			}
   		}
		return null;
   	}

    /* busco el camino desde el inodo hasta el root de forma recursiva */
    
    public void getPathToRoot(Inode<T> current, List<T> path){
    	/* agrego el nombre del inodo siempre al principio asi queda ordenado el camino en la lista */
    	path.add(0, current.getData());
    	if (current.getParent() != null) {
    		getPathToRoot(current.getParent(), path);
    	}
    	return;
    }
    
}
