package org.team1100.input;

import org.team1100.RobotMap;

import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.USBCamera;

public class MicrosoftCamera extends USBCamera {
	private static MicrosoftCamera camera;
	private Image currentFrame;

	public static MicrosoftCamera getInstance() {
		if (camera == null) {
			camera = new MicrosoftCamera(RobotMap.CAMERA_NAME);
		}
		return camera;
	}

	private MicrosoftCamera(String name) {
		super(name);
	}

	public void start() {
		CameraServer.getInstance().startAutomaticCapture(this);
	}
	
	public Image getCurrentFrame(){
		getImage(currentFrame);
		return currentFrame;
	}
}
