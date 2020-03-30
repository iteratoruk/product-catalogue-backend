/*
 * Copyright 2020 Iterator Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package iteratoruk.product.catalogue.contracts;

import static java.util.Arrays.asList;

import java.util.List;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import iteratoruk.product.catalogue.Category;
import iteratoruk.product.catalogue.CategoryRepository;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
abstract class PrePopulatedDatabaseContractTest {

  private static final List<Category> CATEGORIES =
      asList(
          Category.builder().name("foo").build(),
          Category.builder().name("bar").build(),
          Category.builder().name("baz").build());

  @Autowired private CategoryRepository categoryRepository;

  @Autowired private MockMvc mvc;

  @BeforeEach
  void setup() {
    RestAssuredMockMvc.mockMvc(mvc);
    categoryRepository.deleteAll();
    categoryRepository.saveAll(CATEGORIES);
  }
}
