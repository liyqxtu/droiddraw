package org.droiddraw.gui;

import java.util.prefs.BackingStoreException;

import org.droiddraw.AndroidEditor;
import org.droiddraw.AndroidEditor.ScreenMode;

public class Preferences {
	public static enum Layout {ABSOLUTE, LINEAR, RELATIVE}
	public static enum Update {YES, ASK, NO}
	
	private static String SNAP = "snap";
	private static String SCREEN = "screen";
	private static String LAYOUT = "layout";
	private static String UPDATE = "update";
	
	protected boolean snap;
	protected ScreenMode screen;
	protected Layout layout;
	protected Update updateCheck;
	
	public Preferences() {
		this.snap = false;
		this.screen = ScreenMode.HVGA_PORTRAIT;
		this.layout = Layout.ABSOLUTE;
		this.updateCheck = Update.ASK;
	}
	
	public void load() {
		java.util.prefs.Preferences prefs = java.util.prefs.Preferences.userNodeForPackage(Preferences.class);
		this.snap = prefs.getBoolean(SNAP, false);
		
		screen = ScreenMode.values()[prefs.getInt(SCREEN, 3)];
		layout = Layout.values()[prefs.getInt(LAYOUT, 0)];	
		updateCheck = Update.values()[prefs.getInt(UPDATE, 1)];
	}
	
	public void save() {
		java.util.prefs.Preferences prefs = java.util.prefs.Preferences.userNodeForPackage(Preferences.class);
		
		prefs.putBoolean(SNAP, snap);
		prefs.putInt(SCREEN, screen.ordinal());
		prefs.putInt(LAYOUT, layout.ordinal());
		prefs.putInt(UPDATE, updateCheck.ordinal());
		try {
			prefs.sync();
		}
		catch (BackingStoreException ex) {
			AndroidEditor.instance().error(ex);
		}
	}
	
	public void setSnap(boolean value) {
		this.snap = value;
	}
	
	public boolean getSnap() {
		return snap;
	}
	
	public void setScreenMode(ScreenMode screen) {
		this.screen = screen;
	}
	
	public ScreenMode getScreenMode() {
		return screen;
	}
	
	public void setDefaultLayout(Layout l) {
		this.layout = l;
	}
	
	public Layout getDefaultLayout() {
		return layout;
	}

	public Update getUpdateCheck() {
	  return updateCheck;
	}
	
	public void setUpdateCheck(Update u) {
	  this.updateCheck = u;
	}
}
