/*
 * Copyright 2018 Tallence AG
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tallence.formeditor.cae.validator;

import com.tallence.formeditor.cae.elements.ComplexValue;
import com.tallence.formeditor.cae.elements.SelectBox;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Validator for elements of type {@link SelectBox}
 */
public class SelectBoxValidator implements Validator<String> {

  private final SelectBox selectBox;

  private boolean mandatory;

  public SelectBoxValidator(SelectBox selectBox) {
    this.selectBox = selectBox;
  }

  @Override
  public List<String> validate(String value) {

    List<String> errors = new ArrayList<>();

    if (StringUtils.hasText(value)) {

      List<String> values = new ArrayList<>();
      for (ComplexValue complexValue : this.selectBox.getOptions()) {
        values.add(complexValue.getValue());
      }

      if (!values.contains(value)) {
        throw new InvalidGroupElementException("A Select-Option was chosen, which does not exist in Form Element!");
      }
    } else if (this.mandatory) {
      errors.add("com.tallence.forms.selectbox.empty");
    }

    return errors;
  }

  @Override
  public boolean isMandatory() {
    return this.mandatory;
  }

  public void setMandatory(boolean mandatory) {
    this.mandatory = mandatory;
  }
}
