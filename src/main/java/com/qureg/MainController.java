package com.qureg;


import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {
    @FXML
    private Label mainLabel;

    @FXML
    private TextField card_id;

    @FXML
    private TextField firstname;

    @FXML
    private TextField surname;

    @FXML
    private TextArea info;

    @FXML
    private Button regBtn;

    @FXML
    private Button delBtn;

    @FXML
    private ListView<Person> lvMain;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DBConnector dc = DBConnector.getDC();
        lvMain.getItems().addAll(dc.getPeopleList());
        regBtn.disableProperty().bind(
                Bindings.createBooleanBinding( () -> firstname.getText().trim().isEmpty(), firstname.textProperty()
                )
                        .or(Bindings.createBooleanBinding(() -> surname.getText().trim().isEmpty(), surname.textProperty()))
                        .or(Bindings.createBooleanBinding(() -> info.getText().trim().isEmpty(), info.textProperty()))
                        .or(Bindings.createBooleanBinding(() -> card_id.getText().trim().isEmpty(), card_id.textProperty()))
        );
        delBtn.disableProperty().bind(lvMain.getSelectionModel().selectedItemProperty().isNull());
    }

    @FXML
    private void handleRegBtnAction(ActionEvent event) {
        DBConnector.writeToDB(Integer.parseInt(card_id.getText()), firstname.getText(), surname.getText(), info.getText());
        DBConnector dc = DBConnector.getDC();
        lvMain.getItems().clear();
        lvMain.getItems().addAll(dc.getPeopleList());
    }

    @FXML
    private void handleDelBtnAction(ActionEvent event) {
        String sId = String.valueOf(lvMain.getSelectionModel().getSelectedItem());
        String newSID = sId.substring(0, sId.indexOf(" ⠀"));
        Integer intID = Integer.parseInt(newSID);
        DBConnector.delFromDB(intID);

        DBConnector dc = DBConnector.getDC();

        lvMain.getItems().clear();
        lvMain.getItems().addAll(dc.getPeopleList());
    }
}