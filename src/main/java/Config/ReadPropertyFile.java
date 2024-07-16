package Config;

import org.aeonbits.owner.Config;


//@Sources({
//    "classpath:qa.properties" // mention the property file name
//})

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"file:${user.dir}/src/main/java/Config/config.properties"})


public interface ReadPropertyFile extends Config {

	String environment();

	@Key("${environment}.url")
	String url();
	
	@Key("${environment}.api_url")
    String api_url();

	@Key("${environment}.user_id")
    int user_id();

	@Key("${environment}.ie_database_db")
	String ie_database_db();

	@Key("${environment}.ie_database_dw")
	String ie_database_dw();

	@Key("${environment}.ie_database_username")
	String ie_database_username();

	@Key("${environment}.ie_database_password")
	String ie_database_password();

	String ie_username();
    String ie_password();

    String version();
    
    String runmode();

	@Key("${environment}.webAPI")
	String webAPI();

	String swaggerUI();

//    @Key("db.hostname")
//    String getDBHostname();
//
//    @Key("db.port")
//    int getDBPort();
//
//    @Key("db.username")
//    String getDBUsername();
//
//    @Key("db.password")
//    String getDBPassword();
    
    

}