;;; pangram.el --- Pangram (exercism)  -*- lexical-binding: t; -*-

;;; Commentary:

(defun is-pangram (phrase)
  (let ((charset (->>
                  phrase
                  (downcase)
                  (replace-regexp-in-string "[^a-z]" "")
                  (string-to-list)
                  (cl-remove-duplicates))))
    (= 26 (length charset))))


(provide 'pangram)
;;; pangram.el ends here
