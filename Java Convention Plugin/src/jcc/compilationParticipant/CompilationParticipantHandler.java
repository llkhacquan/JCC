package jcc.compilationParticipant;

import java.util.Vector;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.compiler.BuildContext;
import org.eclipse.jdt.core.compiler.CompilationParticipant;

import core.JCCHandler;
import core.warning.Warning;

public class CompilationParticipantHandler extends CompilationParticipant {

	public static String MARKER_TYPE = IMarker.PROBLEM;

	// private static final String MARKER_ID =
	// FavoritesPlugin.ID + ".jccmarker";

	private JCCHandler jccHandler = new JCCHandler(System.in);

	public CompilationParticipantHandler() {

	}

	@Override
	public void cleanStarting(IJavaProject project) {
		try {
			project.getProject().deleteMarkers(MARKER_TYPE, true,
					IResource.DEPTH_INFINITE);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void buildStarting(BuildContext[] files, boolean isBatch) {
		for (int i = 0; i < files.length; i++) {
			check(files[i].getFile());
		}
	}

	@Override
	public int aboutToBuild(IJavaProject project) {
		return READY_FOR_BUILD;
	}

	private void check(IResource resource) {
		try {
			resource.deleteMarkers(MARKER_TYPE, true, IResource.DEPTH_INFINITE);
		} catch (CoreException e) {
			// something went wrong
		}

		if (resource instanceof IFile) {
			if (((IFile) resource).getFileExtension().equals("java")) {
				Vector<Warning> w;
				IFile file = (IFile) resource;
				try {
					w = jccHandler.check(file.getContents(),
							JCCHandler.CHECK_TYPE_OTHER);
					// w.addAll(jccHandler.check(file.getContents(),
					// JCCHandler.CHECK_TYPE_COMMENT));
					// w.addAll(jccHandler.check(file.getContents(),
					// JCCHandler.CHECK_TYPE_NAMING));
					// w.addAll(jccHandler.check(file.getContents(),
					// JCCHandler.CHECK_TYPE_INDENT));
					for (int i = 0; i < w.size(); i++) {
						IMarker marker = file.createMarker(MARKER_TYPE);
						marker.setAttribute(IMarker.LOCATION,
								"line " + w.elementAt(i).pos.beginLine);
						marker.setAttribute(IMarker.MESSAGE, w.elementAt(i)
								.toString());
						marker.setAttribute(IMarker.LINE_NUMBER,
								w.elementAt(i).pos.beginLine);

						marker.setAttribute(IMarker.SEVERITY,
								IMarker.SEVERITY_INFO);
					}
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}
			}
			return;
		}
		try {
			IResource children[] = ((IContainer) resource).members();
			for (int i = 0; i < children.length; i++) {
				check(children[i]);
			}
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean isActive(IJavaProject project) {
		return true;
	}
}
