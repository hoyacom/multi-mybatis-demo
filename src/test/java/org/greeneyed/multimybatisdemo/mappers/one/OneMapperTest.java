/**
 * <br>
 * Created Date : Nov 14, 2018 7:27:46 PM Copyright©2002 Nbware All rights reserved.
 * 
 * @author hoyacom
 */
package org.greeneyed.multimybatisdemo.mappers.one;

import static org.assertj.core.api.Assertions.assertThat;

import org.greeneyed.multimybatisdemo.config.OneDatabaseConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * <br>
 * Created Date : Nov 14, 2018
 * 
 * @author hoyacom
 * @since
 *
 */
@ActiveProfiles("DEV")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {OneDatabaseConfiguration.class})
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OneMapperTest
{
  protected Logger  log = LoggerFactory.getLogger(OneMapperTest.class);

  @Autowired
  private OneMapper oneMapper;

  /**
   * Test method for {@link org.greeneyed.multimybatisdemo.mappers.one.OneMapper#getVersion()}.
   */
  @Test
  public void testGetVersion()
  {
    String version = oneMapper.getVersion();
    System.out.println("version=" + version);
    assertThat(version).isEqualTo("H2TESTDB 1.4.196");
  }
}
