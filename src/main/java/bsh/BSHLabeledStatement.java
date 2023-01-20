/* Generated by: JJTree: Do not edit this line. BSHLabeledStatement.java Version 1.1 */
/* ParserGeneratorCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=false,TRACK_TOKENS=false,NODE_PREFIX=BSH,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
/** Copyright 2023 Nick nickl- Lombard
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

import bsh.legacy.*;

public class BSHLabeledStatement extends SimpleNode implements ParserConstants {
    public String label;

    public Object eval( CallStack callstack, Interpreter interpreter ) throws EvalError {
        if (getChildCount() > 0) {

            // pass continue labels onto loops to handle in loop
            if (getChild(0) instanceof BSHForStatement)
                ((BSHForStatement)getChild(0)).label = label;
            else if (getChild(0) instanceof BSHEnhancedForStatement)
                ((BSHEnhancedForStatement)getChild(0)).label = label;
            else if (getChild(0) instanceof BSHWhileStatement)
                ((BSHWhileStatement)getChild(0)).label = label;

            Object retrn = getChild(0).eval(callstack, interpreter);

            if (retrn instanceof ReturnControl) {
                ReturnControl control = (ReturnControl)retrn;
                switch (control.kind) {
                    case CONTINUE:
                        if (label.equals(control.label))
                            throw new EvalError( // if the loop missed label there is no loop
                                "Continue cannot be used outside of a loop", this, callstack);
                        return retrn; // not our label send it up
                    case BREAK:
                        if (!label.equals(control.label))
                            return retrn; // not our label send it up
                        break;
                    default:
                        return retrn; // no further label controls send up
                }
            }
        }
        return Primitive.VOID;
    }

    public String toString() {
        return super.toString() + ": " + label + ":";
    }
}
/* ParserGeneratorCC - OriginalChecksum=38819a41232c844db8da78bbd92f9430 (do not edit this line) */
