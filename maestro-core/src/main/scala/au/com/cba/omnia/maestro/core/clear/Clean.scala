//   Copyright 2014 Commonwealth Bank of Australia
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.

package au.com.cba.omnia.maestro.core
package clean

import au.com.cba.omnia.maestro.core.data._

case class Clean(run: (Field[_, _], String) => String)

object Clean {
  def all(cleans: Clean*): Clean =
    Clean((field, data) => cleans.foldLeft(data)((acc, clean) => clean.run(field, acc)))

  def trim: Clean =
    Clean((_, data) => data.trim)

  def removeNonPrintables: Clean =
    Clean((_, data) => data.replaceAll("[^\\p{Print}]", ""))

  def default: Clean =
    Clean((_, data) => data.trim.replaceAll("[^\\p{Print}]", ""))
}
