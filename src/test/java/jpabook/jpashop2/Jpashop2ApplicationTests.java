package jpabook.jpashop2;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.internal.log.SubSystemLogging;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

@SpringBootTest
@Slf4j
class Jpashop2ApplicationTests {

	@Test
	void start(){
		try{
			contextLoads();
		}
		catch (RuntimeSQLException e){
			//log.info("오류={}",e.getMessage(),e);
			log.info("오류= ",e);
		}
	}

	void contextLoads() {
		try{
			throw new SQLException();
		} catch (SQLException e) {
			throw new RuntimeSQLException("얌마 똑바로해",e);
			//throw new RuntimeSQLException(e);
		}

	}


	public static class RuntimeSQLException extends RuntimeException{
		public RuntimeSQLException(String message, Throwable cause) {
			super(message, cause);
		}

		public RuntimeSQLException(Throwable cause) {
			super(cause);
		}
	}
}
