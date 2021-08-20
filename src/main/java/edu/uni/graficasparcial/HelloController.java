package edu.uni.graficasparcial;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.util.Random;

public class HelloController {
    int maxX, maxY,
        height, width,
        xUnity, yUnity;

    private final Color GRAPH_COLOR = Color.rgb(255, 255, 255);
    GraphicsContext gtx;

    @FXML
    private TextField txtXMax;

    @FXML
    private TextField txtYMax;


    @FXML
    private TextField txtXValue;

    @FXML
    private TextField txtYValue;

    @FXML
    private Button btnSetValues;

    @FXML
    private Canvas canvasGraph;


    @FXML
    void setValues(ActionEvent __) {

        if (!txtXMax.isDisable()) {
            if (isNotNumber(txtXMax.getText()) || isNotNumber(txtYMax.getText())) return;
            init();

            txtXMax.setDisable(true);
            txtYMax.setDisable(true);
            paintSeparators();
        }

        if (isNotNumber(txtXValue.getText()) || isNotNumber(txtYValue.getText())) return;

        var x = Integer.parseInt(txtXValue.getText());
        var y = Integer.parseInt(txtYValue.getText());

        if (x > maxX || y > maxY) return;

        var rand = new Random();
        gtx.setFill(Color.rgb(34,51,51));
        gtx.fillRect(x*xUnity, 0, xUnity,  height);
        gtx.setFill(Color.rgb(100 + rand.nextInt(155),100 + rand.nextInt(155), 100 + rand.nextInt(155)));
        gtx.fillRect(x*xUnity, height - (y*yUnity), xUnity,  height);
        paintSeparators();
    }

    private void init() {
        gtx = canvasGraph.getGraphicsContext2D();
        gtx.setStroke(GRAPH_COLOR);
        int WITH_STROKE = 2;
        gtx.setLineWidth(WITH_STROKE);
        height = (int) canvasGraph.getHeight();
        width = (int) canvasGraph.getWidth();
        maxX = Integer.parseInt(txtXMax.getText());
        maxY = Integer.parseInt(txtYMax.getText());
        yUnity = (height / maxY);
        xUnity = (width / maxX);
    }

    private void paintSeparators() {
        for (int i = 1; i < (maxX + 1); i++) {
            var xPos = xUnity * i;
            gtx.strokeLine(xPos, height, xPos, height - 15);
        }

        for (int i = 0; i < maxY; i++) {
            var yPos = yUnity * i;
            gtx.strokeLine(0, yPos, 15, yPos);
        }
    }

    private boolean isNotNumber(String number) {
        try {
            Integer.parseInt(number);
            return false;
        } catch (NumberFormatException ex) {
            return true;
        }
    }
}