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
package iteratoruk.product.catalogue;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class CategoryRepositoryTest {

  @Autowired private CategoryRepository repo;

  @Autowired private EntityManager em;

  @Test
  @DisplayName("should insert category when save")
  void shouldInsertCategory_whenSave() {
    // given
    Category c = Category.builder().name("Books").build();
    // when
    Category saved = repo.save(c);
    // then
    assertThat(em.find(Category.class, saved.getId())).isEqualTo(saved);
  }

  @Test
  @DisplayName("should return saved category when find by ID")
  void shouldReturnSavedCategory_whenFindById() {
    // given
    Category c = Category.builder().name("Books").build();
    em.persist(c);
    // when
    Category found = repo.findById(c.getId()).get();
    // then
    assertThat(found).isEqualTo(c);
  }

  @Test
  @DisplayName("should return all saved categories when find all")
  void shouldReturnAllSavedCategories_whenFindAll() {
    // given
    List<Category> categories =
        asList(
            Category.builder().name("foo").build(),
            Category.builder().name("bar").build(),
            Category.builder().name("baz").build());
    categories.forEach(c -> em.persist(c));
    // when
    List<Category> found = repo.findAll();
    // then
    assertThat(found).containsOnly(categories.toArray(new Category[0]));
  }
}
