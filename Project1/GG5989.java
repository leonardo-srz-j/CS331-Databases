import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.HashSet;

public class GG5989 extends Application {
    private ToggleGroup radio_buttons_group;
    private RadioButton add_vertex, add_edge, move_vertex, shortest_path, change_weight;
    private Button add_all_edges, random_weights, minimal_spanning_tree, help;
    private TextField weight_value;
    private Circle start_point, end_point;
    private Line current_Line;
    private HashMap<Circle, HashSet<Line>> saved_objects;
    private BorderPane main_pane;
    private VBox button_box;
    private AnchorPane graph;
    private Scene scene;

    public void myGui() {

        //HashMap that takes a Circle as a Key and a set of Edges as a Value
        //Edges weren't added to the HashMap
        saved_objects = new HashMap<>();

        //--------------Panes------------------------
        main_pane = new BorderPane();
        button_box = new VBox();
        graph = new AnchorPane();

        //-------Set_Of_Radio_Buttons----------------
        radio_buttons_group = new ToggleGroup();
        //Add Vertex
        add_vertex = new RadioButton("Add Vertex");
        add_vertex.setToggleGroup(radio_buttons_group);
        //Add Edge
        add_edge = new RadioButton("Add Edge");
        add_edge.setToggleGroup(radio_buttons_group);
        //More Vertex
        move_vertex = new RadioButton("Move Vertex");
        move_vertex.setToggleGroup(radio_buttons_group);
        //Shortest Path
        shortest_path = new RadioButton("Shortest Path");
        shortest_path.setToggleGroup(radio_buttons_group);
        //Changing Weights
        change_weight = new RadioButton("Change a weight to: ");
        change_weight.setToggleGroup(radio_buttons_group);
        weight_value = new TextField();

        //--------Set_Of_Regular_Buttons-----------------------
        add_all_edges = new Button("Add All Edges");
        random_weights = new Button("Random Weights");
        minimal_spanning_tree = new Button("Minimal Spanning Tree");
        help = new Button("Help");

        //---------------Disabled_Nonworking_Features----------
        shortest_path.setDisable(true);
        change_weight.setDisable(true);
        weight_value.setDisable(true);
        add_all_edges.setDisable(true);
        random_weights.setDisable(true);
        minimal_spanning_tree.setDisable(true);
        help.setDisable(true);

        //---------------Setting_Width
        add_all_edges.setMaxWidth(Double.MAX_VALUE);
        random_weights.setMaxWidth(Double.MAX_VALUE);
        minimal_spanning_tree.setMaxWidth(Double.MAX_VALUE);
        help.setMaxWidth(Double.MAX_VALUE);

        //---------------Setting_Elements-------------------------
        button_box.prefWidth(200);
        button_box.maxWidthProperty();
        button_box.setSpacing(10);
        button_box.setPadding(new Insets(60, 20, 0, 5));
        button_box.getChildren().addAll(add_vertex,
                add_edge, move_vertex, shortest_path,
                change_weight, weight_value, add_all_edges, random_weights, minimal_spanning_tree, help);
        main_pane.setLeft(button_box);
        main_pane.setCenter(graph);

        /*
        *
        * Working function for
        * Adding Vertex
        */
        add_vertex.setOnAction(event -> graph.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent me) -> {
            Circle currentCircle;
            weight_value.setDisable(true);
            if (add_vertex.isSelected()) {
                if (me.getButton().equals(MouseButton.PRIMARY)) {
                    currentCircle = new Circle(me.getX(), me.getY(), 7, Color.RED);
                    saved_objects.put(currentCircle, new HashSet<>());
                    graph.getChildren().add(currentCircle);
                }
            }
        }));//end of Adding Vertex on Button Action

        /*
        *
        * Working function for
        * Moving Vertex
        */
        move_vertex.setOnAction(event -> graph.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent me) -> {
            weight_value.setDisable(true);
            if (move_vertex.isSelected()) {
                if (me.getButton().equals(MouseButton.PRIMARY)) {
                    if (start_point == null){
                        try{ for (Circle c : saved_objects.keySet()){
                                if (c.contains(me.getX(),me.getY())) {
                                    start_point = c;
                                    start_point.setFill(Color.GREENYELLOW);
                                }
                            }
                            if (start_point == null) throw new NullPointerException();
                            } catch (NullPointerException ex){return;}
                        start_point.setFill(Color.GREENYELLOW);
                        graph.layout();
                    }else if(start_point.getFill().equals(Color.GREENYELLOW)){
                        start_point.setCenterX(me.getX());
                        start_point.setCenterY(me.getY());
                        start_point.setFill(Color.RED);
                        graph.layout();
                        start_point = null;
                    }
                }
            }
        }));//end of Move Vertex on Button Action

        /*
        *
        * Working function for
        * Adding Edges
        */
        add_edge.setOnAction(event -> graph.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent me) -> {
            weight_value.setDisable(true);
            if (add_edge.isSelected()) {
                if(start_point ==null) {
                    for (Circle c : saved_objects.keySet()){
                        if (c.contains(me.getX(),me.getY())){
                            start_point = c;
                        }
                    }
                    try{
                        start_point.setFill(Color.GREENYELLOW);
                    }catch (NullPointerException ex) {
                        graph.layout();
                        return;
                    }
                    graph.layout();
                }
                else if (start_point != me.getTarget() && start_point.getFill().equals(Color.GREENYELLOW) && end_point == null) {
                    for (Circle c : saved_objects.keySet()) {
                        if (c.contains(me.getX(), me.getY())) {
                            end_point = c;
                            end_point.setFill(Color.GREENYELLOW);
                            current_Line = new Line();
                            current_Line.startXProperty().bind(start_point.centerXProperty());
                            current_Line.startYProperty().bind(start_point.centerYProperty());
                            current_Line.endXProperty().bind(end_point.centerXProperty());
                            current_Line.endYProperty().bind(end_point.centerYProperty());
                            current_Line.setStroke(Color.BLUE);
                            current_Line.setStrokeWidth(3);
                            graph.getChildren().add(current_Line);
                            start_point.setFill(Color.RED);
                            end_point.setFill(Color.RED);
                            start_point = null;
                            end_point = null;
                            return;
                        }
                    }
                }
            }
        }));//end of Add Edge on Button Action

        /*
        * Non working function for
        * Shortest_Path
        */
        shortest_path.setOnAction(event -> {
            graph.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent me) -> {
                double x = me.getX();
                double y = me.getY();
                Circle a_point;
                Circle b_point;
                weight_value.setDisable(true);
                if (shortest_path.isSelected()) {
                    if (me.getButton().equals(MouseButton.PRIMARY)) {
                    }
                }
            });
        });//end of Shortest Path on Button Action

        /*
        * Non working function for
        * Change_Weight
        */
        change_weight.setOnAction(event -> {
            if (change_weight.isSelected()) {
                graph.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent me) -> {
                    weight_value.setDisable(false);
                    String number = weight_value.getText();
                    System.out.println("this is number: " + number);
                    if (change_weight.isSelected() && number == null) {
                        if (me.getButton().equals(MouseButton.PRIMARY)) {
                            System.out.println(me.getTarget());
                        }
                    }
                });
            }
        });//end of change weight on Button Action

    }//end of myGUI


    public void GG5989() { myGui(); }

    public void start(Stage primaryStage) {
        GG5989();
        scene = new Scene(main_pane, 900, 900);
        primaryStage.setTitle("GG5989");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) { Application.launch(args); }
}