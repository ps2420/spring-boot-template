package com.amaris.ai.cloud.search.controller;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import com.amaris.ai.cloud.search.BaseSetup;
import com.amaris.ai.cloud.search.ITTestSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ITTestSetup.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"local"})
public class SearchControllerTest extends BaseSetup {

}
