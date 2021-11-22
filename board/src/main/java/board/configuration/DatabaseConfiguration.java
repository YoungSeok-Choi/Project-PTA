package board.configuration;

import javax.sql.DataSource; // 중요함.  

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:/application.properties") // Properties sources
@EnableTransactionManagement // Transaction
public class DatabaseConfiguration {
	
	@Autowired
	private ApplicationContext applicationContext; // It's really really basic to start
	
	// from application.properties to register the configuration file 
	@Bean
	@ConfigurationProperties(prefix="spring.datasource.hikari") //  designate the data source configuration file with prefix 
	public HikariConfig hikariConfig() {
		return new HikariConfig();	 
	} 
	
	@Bean
	public DataSource dataSource() throws Exception{
		DataSource dataSource = new HikariDataSource(hikariConfig()); // connection data source
		System.out.println(dataSource.toString()); // to check the connection, with hash code
		return dataSource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean(); // spring-mybatis에서는 해당 bean으로 SqlSessionFactory를 생성.
		sqlSessionFactoryBean.setDataSource(dataSource); // 앞에서 만든 데이터 소스를 설정한다.
		
		// mapper 파일의 위치 설정, mapper/ 경로 이하의 모든 sql-로 시작하여 .xml로 끝나는 파일을 가져오라.
		// mapper는 어플리케이션에서 사용할 sql을 담고 있는 xml 파일이다.
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/**/sql-*.xml"));
		
		// mapUnderscroeToCamelCase 설정 적용 이후 아래 코드 추가하여 불러낸다.
		sqlSessionFactoryBean.setConfiguration(myBatisConfig());
		
		return sqlSessionFactoryBean.getObject();
	}
	
	// sqlSessionTemplate 호출시 sqlSessionFactory를 상속 받고 mapper를 연동하여 return 
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
	// mapUnderscroeToCamelCase 설정 적용, bean registration
	@Bean
	@ConfigurationProperties(prefix = "mybatis.configuration") // application.properties의 설정 중 마이바티스에 관련된 설정을 가져온다.
	public org.apache.ibatis.session.Configuration myBatisConfig(){
		return new org.apache.ibatis.session.Configuration(); // 가져온 설정을 자바 클래스로 만들어 반환한다.
	};
	
	@Bean
	public PlatformTransactionManager transactionManager() throws Exception{
		return new DataSourceTransactionManager(dataSource());
	}
	
	
}
