(ns brainfuck.compiler)

(defn parse [text]
  (loop [stack nil, [op & text] text, code nil]
    (cond
     (nil? op)           (reverse code)
     ((set "+-.,><") op) (recur stack text (conj code [op]))
     (= \[ op)           (recur (conj stack code) text nil)
     (= \] op)           (let [[top & btm] stack]
                             (recur btm text (conj top [\[ (reverse code)])))
     :else               (recur stack text code))))

(defn exec [{:keys [reg pos] :as vm} [op code]]
  (case op
    \+ (update-in vm [:reg pos] (fnil inc 0))
    \- (update-in vm [:reg pos] (fnil dec 0))
    \. (do (print (char (nth reg pos))) vm)
    \, (assoc-in vm [:reg pos] (.read *in*))
    \> (let [i (inc pos)]
         (assoc vm :pos i :reg (if (>= i (count reg)) (conj reg 0) reg)))
    \< (update-in vm [:pos] dec)
    \[ (loop [{:keys [reg pos] :as vm} vm]
         (if (zero? (nth reg pos)) vm (recur (reduce exec vm code))))
    vm))

(defn run [text]
  (reduce exec {:reg [0] :pos 0} (parse text)))
