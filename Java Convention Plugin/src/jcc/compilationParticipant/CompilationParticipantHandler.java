package jcc.compilationParticipant;

import java.util.Vector;

import jcc.preferences.PreferencesPageHandler;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.compiler.BuildContext;
import org.eclipse.jdt.core.compiler.CompilationParticipant;

import core.CheckOptions;
import core.CoreHandler;
import core.rules.RulesManager;
import core.warning.Warning;

public class CompilationParticipantHandler extends CompilationParticipant {

	public static String MARKER_TYPE = IMarker.PROBLEM;
	private RulesManager rm = RulesManager
			.createRulesManager(PreferencesPageHandler.getRulesFile());

	private void reloadRulesManager() {
		rm = RulesManager.createRulesManager(PreferencesPageHandler
				.getRulesFile());
	}

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
				Vector<Warning> w = new Vector<Warning>();
				IFile file = (IFile) resource;
				try {
					if (PreferencesPageHandler.reloadRulesWhenCheck())
						reloadRulesManager();
					for (CheckOptions o : CheckOptions.values())
						if (o != CheckOptions.CHECK_TYPE_INDENT)
							w.addAll(CoreHandler.check(file.getContents(), rm,
									o));

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
