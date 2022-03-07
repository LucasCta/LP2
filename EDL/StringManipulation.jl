text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
findWord = findfirst("dolor", text)
replacingWord = replace(text, "amet," => "dolor")
regexFind = replace(text, r"a[\w]*g" => "eliiiit")
regexCollect = collect(m.match for m = eachmatch(r"[\w]{5,}", text))
textMult = text^3
