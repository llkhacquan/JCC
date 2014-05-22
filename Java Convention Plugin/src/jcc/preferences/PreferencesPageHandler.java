package jcc.preferences;

import jcc.Activator;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.sun.xml.internal.ws.spi.db.FieldSetter;

import core.rules.RulesManager;

/**
 * This class represents a preference page that is contributed to the
 * Preferences dialog. By subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows us to create a page
 * that is small and knows how to save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They are stored in the
 * preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 */

public class PreferencesPageHandler extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	private FileFieldEditor rulesFile;

	public PreferencesPageHandler() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("This page contains preferences for JCC");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
		Activator.getDefault().getPreferenceStore();

	}

	@Override
	protected void createFieldEditors() {
		// TODO Auto-generated method stub
		rulesFile = new FileFieldEditor(PreferenceConstants.P_RULES_FILE,
				"Rules file", true, getFieldEditorParent());
		addField(rulesFile);
	}

	@Override
	public boolean performOk() {
		return super.performOk();
	}

	public static String getRulesFile() {
		return Activator.getDefault().getPreferenceStore()
				.getString(PreferenceConstants.P_RULES_FILE);
	}
}