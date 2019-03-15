(ns twenty48.core
  (:gen-class))

(def four-times-zero (repeat 4 0))
(def remove-zero (partial remove zero?))
(def rotate-90 (partial apply map list))

(def move-left (comp (partial take 4)
                      (partial flatten) 
                      (partial conj (repeat 4 0))
                      (partial map (partial apply +))
                      (partial mapcat (partial partition-all 2))
                      (partial partition-by identity)
                      (partial remove-zero)))

(def move-right (comp (partial reverse) (partial move-left) (partial reverse)))

(def move-up
  (comp rotate-90 (partial map (partial move-left)) (partial apply (partial map list))))

(def move-down
 (comp rotate-90 (partial map (partial move-right)) (partial apply (partial map list))))


; ===========================================
(defn move-grid-right
  "Moves an entire grid to the right"
  [grid]
  (map move-right grid))

(defn move-grid-left
  "Moves an entire grid to the left"
  [grid]
  (map move-left grid))

(defn move-grid-down
  "Moves an entire grid down"
  [grid]
  (move-down grid))

(defn move-grid-up
  "Moves an entire grid up"
  [grid]
  (move-up grid))
