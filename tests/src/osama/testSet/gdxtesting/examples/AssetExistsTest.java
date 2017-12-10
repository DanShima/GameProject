

package osama.testSet.gdxtesting.examples;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.Gdx;

import osama.testSet.gdxtesting.TestRunner;

@RunWith(TestRunner.class)
public class AssetExistsTest {

	@Test
	public void isFileExists() {
		assertTrue("This test will only succeed when the apple.png file is in the assets", Gdx.files
				.internal("../android/assets/apple.png").exists());
	}
}
