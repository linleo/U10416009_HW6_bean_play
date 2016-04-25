import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.util.Duration;
import javafx.animation.*;
import javafx.scene.input.KeyCode;
import java.security.SecureRandom;
import java.util.Arrays;

public class BeanMachine extends Application 
{
	Pane pane = new Pane();
	
	// Override the start method in the Application class
	@Override
	public void start(Stage primaryStage) 
	{	
		//create nails
		Circle nail[] = new Circle[28];
		for (int i = 0; i < 28; i++)
		{
			nail[i] = new Circle(8, Color.BLUE);
			if (i == 0)
				nail[i].relocate(190,70);
			else if (i == 1 || i == 2)
				nail[i].relocate((170+(i-1)*40),110);
			else if (i == 3 || i == 4 || i == 5)
				nail[i].relocate((150+(i-3)*40),150);
			else if (i == 6 || i == 7 || i == 8 || i == 9)
				nail[i].relocate((130+(i-6)*40),190);
			else if (i == 10 || i == 11 || i == 12 || i == 13 || i == 14)
				nail[i].relocate((110+(i-10)*40),230);
			else if (i == 15 || i == 16 || i == 17 || i == 18 || i == 19 || i == 20)
				nail[i].relocate((90+(i-15)*40),270);
			else if (i == 21 || i == 22 || i == 23 || i == 24 || i == 25 || i == 26 || i == 27)
				nail[i].relocate((70+(i-21)*40),310);
			pane.getChildren().addAll(nail[i]);
		}
		
		//line under the nails
		Line lineUnderNail[] = new Line[7];
		for(int i = 0; i < 7; i++)
		{
			lineUnderNail[i] = new Line(78+(i*40), 326, 78+(i*40), 380);
			pane.getChildren().addAll(lineUnderNail[i]);
		}
		
		//border of the nails
		Line leftLine = new Line(178, 60, 38, 326);
		leftLine.setStrokeWidth(3);
		Line rightLine = new Line(218, 60, 358, 326);
		rightLine.setStrokeWidth(3);
		Line underLine = new Line(38, 380, 358, 380);
		underLine.setStrokeWidth(3);
		Line line1 = new Line(178, 40, 178, 60);
		line1.setStrokeWidth(3);
		Line line2 = new Line(218, 40, 218, 60);
		line2.setStrokeWidth(3);
		Line line3 = new Line(38, 326, 38, 380);
		line3.setStrokeWidth(3);
		Line line4 = new Line(358, 326, 358, 380);
		line4.setStrokeWidth(3);
				
		pane.getChildren().addAll(rightLine, leftLine, underLine, line1, line2, line3, line4);

		Scene scene = new Scene(pane, 400, 400);
		primaryStage.setTitle("Bean Machine");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false); 
		primaryStage.show();
		
		//mouse listener
		scene.setOnMouseClicked(e -> {
			Circle ball = new Circle(6);
			changeBallColor(ball);
			pane.getChildren().add(ball);
			ball.relocate(192, 30);
			timeline(ball);
		});
		
		//keyboard listener
		scene.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.SPACE){
				Circle ball = new Circle(6);
				changeBallColor(ball);
				pane.getChildren().add(ball);
				ball.relocate(192, 30);
				timeline(ball);
			}
			});
	}

	//timeline
	void timeline(Circle ball)
	{
		Timeline animation = new Timeline(
		new KeyFrame(Duration.millis(1), e -> ballFall(ball)));	
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();
	}
	
	//a method for ball fall
	void ballFall(Circle ball)
	{
		SecureRandom srand = new SecureRandom();
		int x, y, P;
		x = (int)ball.getLayoutX() - (int)ball.getRadius();
		y = (int)ball.getLayoutY() - (int)ball.getRadius() + 1; 
		if (y <= 368)
		{
			if (y == 58 || y == 98 || y == 138 || y == 178 || y == 218 || y == 258 || y == 298)
			{
				P = srand.nextInt(2);
				if (P == 0)
				{
					x-=20;
					ball.relocate(x, y);
				}
				else
				{
					x+=20;
					ball.relocate(x, y);
				}
			}
			else
				ball.relocate(x, y);
		}
	}
	
	//a method to change ball color
	void changeBallColor(Circle ball)
	{
		SecureRandom srand = new SecureRandom();
		int color = srand.nextInt(8);
		if(color == 0)
			ball.setFill(Color.RED);
		else if(color == 1)
			ball.setFill(Color.BLUE);
		else if(color == 2)
			ball.setFill(Color.BLACK);
		else if(color == 3)
			ball.setFill(Color.GREEN);
		else if(color == 4)
			ball.setFill(Color.YELLOW);
		else if(color == 5)
			ball.setFill(Color.PINK);
		else if(color == 6)
			ball.setFill(Color.PURPLE);
		else if(color == 7)
			ball.setFill(Color.SKYBLUE);
	}
}
