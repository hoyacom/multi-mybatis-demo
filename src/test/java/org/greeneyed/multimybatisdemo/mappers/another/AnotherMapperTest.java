/**
 * <br>
 * Created Date : Nov 14, 2018 5:46:57 PM Copyright©2002 Nbware All rights reserved.
 * 
 * @author hoyacom
 */
package org.greeneyed.multimybatisdemo.mappers.another;

import static org.assertj.core.api.Assertions.assertThat;

import org.greeneyed.multimybatisdemo.config.AnotherDatabaseConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * <br>
 * Created Date : Nov 14, 2018
 * 
 * @author hoyacom
 * @since
 *
 */
@Slf4j
@RunWith(SpringRunner.class)
@ActiveProfiles("DEV")
@SpringBootTest(classes = {AnotherDatabaseConfiguration.class})
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AnotherMapperTest
{

  @Autowired
  private AnotherMapper anotherMapper;

  /**
   * Test method for
   * {@link org.org.greeneyed.multimybatisdemo.mappers.another.AnotherMapper#getVersion()}.
   */
  @Test
  public void testGetVersion()
  {
    String version = anotherMapper.getVersion();
    System.out.println("version = " + version);
    log.debug("\nslf4j version = {}", version);
    assertThat(version).isEqualTo("hsqldbtestdb 2.3.5");
  }
}
