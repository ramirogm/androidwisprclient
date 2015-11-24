package com.sputnik.wispr.util;

import java.io.IOException;

import com.sputnik.wispr.logger.WebLogger;

public class FONUtil {
	private static final String FON_MAC_PREFIX = "00:18:84";

	public static boolean isSupportedNetwork(String ssid, String bssid) {
		boolean res = false;

		if (ssid != null) {
			res = isFonera(ssid, bssid) || isNeufBox(ssid, bssid) || isBtFonera(ssid, bssid) || isBtHub(ssid, bssid)
					|| isLivedoor(ssid, bssid) || isSBPublicFonera(ssid, bssid) || isLinkTel(ssid, bssid);
		}

		return res;
	}

	public static boolean isLinkTel(String ssid, String bssid) {
		ssid = FONUtil.cleanSSID(ssid);
		boolean found = ssid != null && (ssid.equalsIgnoreCase("Linktel Wifi"));
		if ( found ) {
			System.out.println("Encontré linktel");
		}
		return found;
	}

	public static boolean isNeufBox(String ssid, String bssid) {
		ssid = FONUtil.cleanSSID(ssid);
		return ssid != null && (ssid.equalsIgnoreCase("NEUF WIFI FON") || ssid.equalsIgnoreCase("SFR WIFI FON"));
	}

	public static boolean isFonera(String ssid, String bssid) {
		ssid = FONUtil.cleanSSID(ssid);
		return ssid != null && ssid.toUpperCase().startsWith("FON_") && !isLivedoor(ssid, bssid);
	}

	public static boolean isSBPublicFonera(String ssid, String bssid) {
		ssid = FONUtil.cleanSSID(ssid);
		return ssid != null && ssid.equalsIgnoreCase("FON");
	}

	public static boolean isBtFonera(String ssid, String bssid) {
		ssid = FONUtil.cleanSSID(ssid);
		return ssid != null && bssid != null && ssid.equalsIgnoreCase("BTFON") && bssid.startsWith(FON_MAC_PREFIX);
	}

	public static boolean isLivedoor(String ssid, String bssid) {
		ssid = FONUtil.cleanSSID(ssid);
		return ssid != null && bssid != null && ssid.equalsIgnoreCase("FON_livedoor")
				&& !bssid.startsWith(FON_MAC_PREFIX);
	}

	public static boolean isBtHub(String ssid, String bssid) {
		ssid = FONUtil.cleanSSID(ssid);
		return ssid != null && bssid != null && ssid.equalsIgnoreCase("BTFON") && !bssid.startsWith(FON_MAC_PREFIX);
	}

	public static boolean haveConnection() throws IOException {
		String blockedUrlText = HttpUtils.getUrl(WebLogger.BLOCKED_URL);
		return blockedUrlText.equals(WebLogger.CONNECTED);
	}

	public static String cleanSSID(String SSID) {
		String res = null;
		if (SSID != null) {
			res = SSID.replace("\"", "");
		}

		return res;
	}
}
