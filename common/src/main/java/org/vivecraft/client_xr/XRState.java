package org.vivecraft.client_xr;

import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;
import org.vivecraft.client_vr.ClientDataHolderVR;
import org.vivecraft.client_vr.gameplay.VRPlayer;
import org.vivecraft.client_vr.provider.openvr_jna.MCOpenVR;

public class XRState {

    public static boolean vrRunning = false;
    public static boolean vrEnabled = true;
    public static boolean vrInitialized = false;

    public static void initializeVR() {
        if (vrInitialized) {
            return;
        }
        vrInitialized = true;
        ClientDataHolderVR dh = ClientDataHolderVR.getInstance();
        dh.vr = new MCOpenVR(Minecraft.getInstance(), dh);
        dh.vr.init();

        dh.vrRenderer = dh.vr.createVRRenderer();
        dh.vrRenderer.lastGuiScale = Minecraft.getInstance().options.guiScale().get();

        dh.vrPlayer = new VRPlayer();
        dh.vrPlayer.registerTracker(dh.backpackTracker);
        dh.vrPlayer.registerTracker(dh.bowTracker);
        dh.vrPlayer.registerTracker(dh.climbTracker);
        dh.vrPlayer.registerTracker(dh.autoFood);
        dh.vrPlayer.registerTracker(dh.jumpTracker);
        dh.vrPlayer.registerTracker(dh.rowTracker);
        dh.vrPlayer.registerTracker(dh.runTracker);
        dh.vrPlayer.registerTracker(dh.sneakTracker);
        dh.vrPlayer.registerTracker(dh.swimTracker);
        dh.vrPlayer.registerTracker(dh.swingTracker);
        dh.vrPlayer.registerTracker(dh.interactTracker);
        dh.vrPlayer.registerTracker(dh.teleportTracker);
        dh.vrPlayer.registerTracker(dh.horseTracker);
        dh.vrPlayer.registerTracker(dh.vehicleTracker);
        dh.vrPlayer.registerTracker(dh.crawlTracker);
        dh.vrPlayer.registerTracker(dh.cameraTracker);

        dh.vr.postinit();
    }

    public static void startVR() {
        GLFW.glfwSwapInterval(0);
    }

    public static void destroyVR() {
        ClientDataHolderVR dh = ClientDataHolderVR.getInstance();

        dh.vr.destroy();
        dh.vr = null;
        dh.vrPlayer = null;
        dh.vrRenderer = null;
        vrInitialized = false;
    }

    public static void pauseVR() {
        //        GLFW.glfwSwapInterval(bl ? 1 : 0);
    }
}
