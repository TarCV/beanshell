/* Generated by: JJTree: Do not edit this line. BSHTryWithResources.java Version 1.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=BSH,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
/** Copyright 2018 Nick nickl- Lombard
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. */
package bsh;

import java.util.ArrayList;
import java.util.List;
import bsh.legacy.*;

public class BSHTryWithResources extends SimpleNode {
    private static final long serialVersionUID = 1L;
    public BSHTryWithResources(int id) { super(id); }

    public Object eval( CallStack callstack, Interpreter interpreter)
            throws EvalError {
        for (int i=0; i < getChildCount(); i++)
            jjtGetChild(i).eval(callstack, interpreter);

        return Primitive.VOID;
    }

    public List<Throwable> autoClose() {
        List<Throwable> thrown = new ArrayList<>();
        for (int i=0; i < getChildCount(); i++) try {
            ((BSHAutoCloseable) jjtGetChild(i)).close();
        } catch (Throwable e) {
            thrown.add(e);
        }
        return thrown;
    }
}
/* JavaCC - OriginalChecksum=08f0fcca24c39792c40d25b047261c1c (do not edit this line) */
