package commandLine;

import java.util.ArrayList;
import java.util.List;

public class runCommand implements commandsInode {
	private Inode<String> currentInode = null;
	
	runCommand(Inode fs){
		this.currentInode = fs;
	}
	
	@Override
	public void pwd() {
		List<String> path = new ArrayList<String>();
		currentInode.getPathToRoot(currentInode, path);
		String fullPath = "/";
		for  (String name: path) {
			fullPath = fullPath + name + "/";
		} 
		System.out.println(fullPath);
		return;
	}
	
	/* method: ls
	 * lista files y directorios en el inode actual 
	 * arg: -r para que sea recursivo
	 * 
	 */
	@Override
	public void ls(String arg) {
		for (Inode nodo : currentInode.getChildren()) {
			System.out.println("./" + nodo.getData() + " " + nodo.getType());
		}
	}

	@Override
	public void mkdir(String arg) {
		if (!currentInode.ChildExists(arg)) {
			currentInode.addChild('d', arg);
		} else {
			System.out.println("Directory already exists");
		}
	}

	@Override
	public void cd(String arg) {
		if (arg.equals("..")) {
			if(currentInode.ParentExists()) {
				currentInode=currentInode.getParent();
				return;
			}
		}
		/* si existe y ademas es un directorio */
		if  (currentInode.ChildExists(arg))  {
			if (currentInode.getChild(arg).getType() != 'f') {
				currentInode=currentInode.getChild(arg);
			} else {
				System.out.println("Directory not found");
			}
		} else {
			System.out.println("Directory not found");
		}
	}

	@Override
	public void touch(String arg) {
		if (!currentInode.ChildExists(arg)) {
			currentInode.addChild('f', arg);
			//System.out.println("File was created");
		} else {
			System.out.println("File already exists");
		}
	}
}
