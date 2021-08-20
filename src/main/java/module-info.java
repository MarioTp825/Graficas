/**
 * Java fx reads for both
 */
module edu.uni.graficasparcial {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens edu.uni.graficasparcial to javafx.fxml, javafx.graphics;
    exports edu.uni.graficasparcial;
}