import pandas as pd
import matplotlib.pyplot as plt
import os, re
from Sastrawi.Stemmer.StemmerFactory import StemmerFactory
import Sastrawi.Stemmer.Filter.TextNormalizer as normalizer
from Sastrawi.StopWordRemover.StopWordRemoverFactory import StopWordRemoverFactory, StopWordRemover, ArrayDictionary
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity

"""Initialization"""

stem = StemmerFactory()
stopwords_factory = StopWordRemoverFactory()
idn_stopword = stopwords_factory.get_stop_words()
stopword_remover = stopwords_factory.create_stop_word_remover()
idn_stemmer = stem.create_stemmer()
path = 'corpus/ID/'
file_list = os.listdir(path)

clean_corpus = []

"""Pre Processing Text

"""

for a, file in enumerate(file_list):
    fname = os.path.splitext(file)[0]
    with open(path + file, 'r', encoding='utf-8') as fh:
        # print(f"Pre Processing file {fname}...")
        fcontent = fh.read()
        fcontent = re.sub(r'[\W\s(0-9)]+', ' ', fcontent.lower())
        # Normalize text using Sastrawi
        fcontent = normalizer.normalize_text(fcontent)
        # Remove stopword using Sastrawi
        fcontent = stopword_remover.remove(fcontent)
        # Stemming using Sastrawi
        fcontent = idn_stemmer.stem(fcontent)
        clean_corpus.append(fcontent)

"""One Hot Encoded Vector"""

# count_vec = CountVectorizer()
# one_HEVect = count_vec.fit_transform(clean_corpus)
# tok = count_vec.get_feature_names_out()
# df_countvect = pd.DataFrame(data=one_HEVect.toarray(), columns=tok)
# df_countvect_transpose = df_countvect.transpose()
# print("One Hot Encoded Vector")
# print(df_countvect)

"""TF-IDF"""

tfidf_vec = TfidfVectorizer()
tfidf_corpus = tfidf_vec.fit_transform(clean_corpus)
# tfidf_term = tfidf_vec.get_feature_names_out()
# df_tfidfvect = pd.DataFrame(data=tfidf_term.toarray(), columns=tfidf_term)
# print("TF-IDF Vector")
# print(df_tfidfvect)

tfidf_query = tfidf_vec.transform(["keraton jogja"])
# tfidf_query_term = tfidf_vec.get_feature_names_out()
# df_tfidfvect_inp = pd.DataFrame(data=tfidf_query_term.toarray(), columns=tfidf_query)
# print("TF-IDF Vector INPUT")

"""Cosine Similarity"""
cos_sim = cosine_similarity(tfidf_query, tfidf_corpus)
print(f"Cosine Similarity antara 'keyword' dengan dokumen lainnya {cos_sim}")