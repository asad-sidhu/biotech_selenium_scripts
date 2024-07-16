package MiscFunctions;

import org.aeonbits.owner.ConfigFactory;
import Config.ReadPropertyFile;

public class Constants {

	public static ReadPropertyFile config = ConfigFactory.create(ReadPropertyFile.class);

	public static String url_loginPage = config.url() + "/user/login";
	public static String url_forgotPassword = config.url() + "/user/forgot-password";
	public static String url_dashboard = config.url() + "/dashboard";
	public static String url_userManagement = config.url() + "/admin/users";
	public static String url_organizationManagement = config.url() + "/admin/organizations";
	public static String url_roleManagement = config.url() + "/admin/roles";
	public static String url_reportManagement = config.url() + "/admin/reports";
	public static String url_notificationSettings = config.url() + "/admin/notifications";
	public static String url_termsManagement = config.url() + "/admin/terms";
	public static String url_productCatalog = config.url() + "/admin/products";
	public static String url_qrCodeManagement = config.url() + "/admin/qrcode";
	public static String url_cycleConfig = config.url() + "/admin/cycle-config";
	public static String url_assetAdmin = config.url() + "/admin/asset";
	public static String url_assetHierarchyAdmin = config.url() + "/admin/asset?tab=hierarchy";
	public static String url_programAdmin = config.url() + "/admin/programs";
	public static String url_interventionAdmin = config.url() + "/admin/interventions";
	public static String url_entityManagement = config.url() + "/admin/entities";
	public static String url_strategyManagement = config.url() + "/admin/strategies?tab=template";
	public static String url_ruleAdmin = config.url() + "/admin/rules";
	public static String url_projectManagement = config.url() + "/admin/projects";
	public static String url_softwareAdmin = config.url() + "/admin/software";
	public static String url_configurationAdmin = config.url() + "/admin/configuration";
	public static String url_templateAdmin = config.url() + "/metadata/templates";
	public static String url_dataAdmin = config.url() + "/client/data-upload";
	public static String url_animalManagement = config.url() + "/metadata/animals";
	public static String url_surveyAdmin = config.url() + "/survey/manage";
	public static String url_reportExplorer = config.url() + "/reports/explorer";

	public static String url_gmailLogin = "https://accounts.google.com/signin/v2/identifier?service=mail";

	public static String reportDirectory = "./test-reports";
	public static String screenshotDirectory = "/Screenshots";

	public static String api_authenticate = config.api_url() + "/auth";
	public static String api_announcementList = config.api_url() + "/announcements";
	public static String api_uploadFile = config.api_url() + "/upload";
	public static String api_initiateAssay = config.api_url() + "/assay/initiate?version=" + config.version();
	public static String api_uploadImage = config.api_url() + "/image/upload?version=" + config.version();
	public static String api_requestFile = config.api_url() + "/file/request?version=" + config.version();
	public static String api_getSettings = config.api_url() + "/settings?version=" + config.version();
	public static String api_listUsers = config.api_url() + "/users/list?version=" + config.version();
	public static String api_checkHeartbeat = config.api_url() + "/heartbeat?version=" + config.version();
	public static String api_fetchLogs = config.api_url() + "/logs";

}
