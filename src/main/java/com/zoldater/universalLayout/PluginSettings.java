package com.zoldater.universalLayout;

import com.intellij.openapi.util.SystemInfo;

import javax.swing.*;

public class PluginSettings {
    private JPanel panel;
    private JComboBox selectLanguageBox;
    private JLabel selectLanguageLabel;
    private JLabel selectKeyLabel;
    private JLabel capitalLetterLabel;
    private JRadioButton capitalShiftButton;
    private JRadioButton capitalCircleButton;
    private JPanel keySelectionPanel;
    private JPanel capitalModePanel;
    private JCheckBox checkBoxCtrl;
    private JCheckBox checkBoxAlt;
    private JCheckBox checkBoxMeta;

    public PluginSettings() {
        ButtonGroup group = new ButtonGroup();
        group.add(capitalShiftButton);
        group.add(capitalCircleButton);

        String ctrlText = SystemInfo.isMac ? "Ctrl" : "^ Ctrl";
        checkBoxCtrl.setText(ctrlText);
        String altText = SystemInfo.isMac ? "⌥ Option" : "Alt";
        checkBoxAlt.setText(altText);
        String metaText = SystemInfo.isMac ? "⌘ Command" : "Win";
        checkBoxMeta.setText(metaText);
    }

    public JPanel getPanel() {
        return panel;
    }

    public boolean isShiftCapitalLetterMode() {
        return capitalShiftButton.isSelected();
    }

    public void setShiftCapitalLetterMode(boolean value) {
        this.capitalShiftButton.setSelected(value);
    }

    public boolean isCircleCapitalLetterMode() {
        return capitalCircleButton.isSelected();
    }

    public void setCircleCapitalLetterMode(boolean value) {
        this.capitalCircleButton.setSelected(value);
    }

    public boolean isCtrlSpecialKeyEnabled() {
        return checkBoxCtrl.isSelected();
    }

    public void setCtrlSpecialKeyEnabled(boolean value) {
        this.checkBoxCtrl.setSelected(value);
    }

    public boolean isAltSpecialKeyEnabled() {
        return checkBoxAlt.isSelected();
    }

    public void setAltSpecialKeyEnabled(boolean value) {
        this.checkBoxAlt.setSelected(value);
    }

    public boolean isMetaSpecialKeyEnabled() {
        return checkBoxMeta.isSelected();
    }

    public void setMetaSpecialKeyEnabled(boolean value) {
        this.checkBoxMeta.setSelected(value);
    }

    public Object getSelectedLanguage() {
        return selectLanguageBox.getSelectedItem();
    }

    public void setSelectedLanguage(Object selectedLanguage) {
        this.selectLanguageBox.setSelectedItem(selectedLanguage);
    }
}
