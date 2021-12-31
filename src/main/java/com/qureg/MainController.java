package com.qureg;


import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {
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
        lvMain.getItems().remove(lvMain.getSelectionModel().getSelectedItem());
    }
}