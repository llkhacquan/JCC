package jcc.preferences;

import jcc.Activator;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

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

public class PreferencesPageHandler extends PreferencePage implements
		IWorkbenchPreferencePage {

	private StyledText styledText;
	
	public PreferencesPageHandler() {
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
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		styledText.setText(store.getString(PreferenceConstants.RulesFileContent));
	}


	@Override
	protected Control createContents(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false,
				false));
		label.setText("Rules");
		styledText = new StyledText(parent, SWT.V_SCROLL
				| SWT.H_SCROLL);
		styledText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		styledText.setText("");
		
		return new Composite(parent, SWT.NULL);
	}
	
	public void 

	private void storeValues() {
		IPreferenceStore store = getPreferenceStore();
		store.setValue(PreferenceConstants.RulesFileContent, styledText.getText());
	}
}	