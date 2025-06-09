;;; pangram.el --- Pangram (exercism)  -*- lexical-binding: t; -*-

;;; Commentary:

;; No threading macro on exercism
;; (defun is-pangram (phrase)
;;   (let ((charset (->>
;;                   phrase
;;                   (downcase)
;;                   (replace-regexp-in-string "[^a-z]" "")
;;                   (string-to-list)
;;                   (cl-remove-duplicates))))
;;     (= 26 (length charset))))

(defun is-pangram (phrase)
  (let* ((phrase-clean (replace-regexp-in-string "[^a-z]" "" (downcase phrase)))
         (charset (cl-remove-duplicates (string-to-list phrase-clean))))
    (= 26 (length charset))))

(provide 'pangram)
;;; pangram.el ends here
