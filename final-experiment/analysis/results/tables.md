
### Run summary

|  | count |
|---|---|
| runs | 480 |
| successful (used) | 445 |
| failed | 32 |
| excluded as leaks | 3 |
| nl runs re-measured after oracle stripping | 22 |

Leaks: jsoup__node-filter-clone__oauto__new-hybrid__sol__run001, jsoup__node-filter-clone__oauto__new-hybrid__terra__run002, jsoup__node-filter-clone__oauto__new-hybrid__terra__run003

### RQ1: mean mutants killed (dev, successful runs; brackets = successful runs when < 3)

| MR | Mutants | Randoop | LLM Luna | LLM Terra | LLM Sol | Hybrid Luna | Hybrid Terra | Hybrid Sol |
|---|---|---|---|---|---|---|---|---|
| days-between | 244 | 5.0 | 6.0 | 6.0 | 6.0 | 6.0 (2) | 6.0 | 6.0 |
| range-length | 534 | 30.0 | 118.3 | 135.7 | 94.7 | 135.0 | 119.3 | 135.7 |
| circle-relate | 299 | 10.0 | 23.0 (2) | 22.3 | 30.7 | 19.5 (2) | 21.3 | 30.3 |
| longitude-normalize | 576 | 15.0 | 21.5 (2) | 21.7 | 21.7 | 21.7 | 21.0 | 23.0 |
| rectangle-contains | 534 | 43.0 | 40.0 (1) | 40.0 | 43.7 | 44.0 (2) | 41.7 | 43.3 |
| renko-scaling | 48 | 7.0 | 10.0 | 10.0 | 10.0 | 10.0 | 10.0 | 10.0 |
| slope-change | 75 | 0.0 | 11.0 (2) | 13.3 | 15.3 | 19.0 | 15.0 | 18.0 |
| node-filter-clone | 58 | 9.0 | 13.7 | 13.7 | 11.0 | 16.7 | 11.3 | 11.0 |
| cleaner-idempotence | 38 | 2.0 | 9.3 | 9.0 | 8.0 | 8.7 | 8.7 | 9.3 |
| matrix-transpose | 34 | 15.0 | 16.7 | 15.7 | 17.7 | 16.0 (2) | 16.7 | 17.3 |

### RQ1: mean covered lines of the class under test

| MR | Randoop | LLM Luna | LLM Terra | LLM Sol | Hybrid Luna | Hybrid Terra | Hybrid Sol |
|---|---|---|---|---|---|---|---|
| days-between | 13.0 | 14.0 | 14.0 | 14.0 | 14.0 (2) | 14.0 | 14.0 |
| range-length | 38.0 | 62.3 | 69.0 | 54.3 | 68.3 | 63.0 | 69.0 |
| circle-relate | 40.0 | 41.0 (2) | 41.0 | 45.0 | 43.0 (2) | 41.0 | 45.0 |
| longitude-normalize | 69.0 | 9.0 (2) | 9.0 | 9.0 | 9.0 | 9.0 | 9.0 |
| rectangle-contains | 38.0 | 33.0 (1) | 33.0 | 33.0 | 38.0 (2) | 33.0 | 33.0 |
| renko-scaling | 39.0 | 138.0 | 139.3 | 140.0 | 138.7 | 140.0 | 140.0 |
| slope-change | 0.0 | 77.0 (2) | 79.7 | 84.0 | 85.7 | 82.7 | 87.0 |
| node-filter-clone | 46.0 | 65.7 | 65.7 | 65.3 | 65.7 | 63.7 | 65.7 |
| cleaner-idempotence | 29.0 | 72.0 | 72.0 | 72.0 | 72.0 | 72.0 | 72.0 |
| matrix-transpose | 37.0 | 37.0 | 37.0 | 37.0 | 37.0 (2) | 37.0 | 37.0 |

### RQ1: relative kill and line coverage score (per MR: mean / best RQ1 configuration mean, averaged over MRs)

| Configuration | Kill score | Line coverage score |
|---|---|---|
| Randoop | 0.53 | 0.68 |
| LLM Luna | 0.88 | 0.87 |
| LLM Terra | 0.90 | 0.88 |
| LLM Sol | 0.90 | 0.87 |
| Hybrid Luna | 0.94 | 0.91 |
| Hybrid Terra | 0.88 | 0.87 |
| Hybrid Sol | 0.96 | 0.90 |
| LLM pooled | 0.89 | 0.88 |
| Hybrid pooled | 0.93 | 0.89 |

### RQ1: test strength (killed / covered mutants, mean over runs)

| Generator | Test strength |
|---|---|
| Randoop | 31% |
| LLM | 39% |
| Hybrid | 41% |

### RQ2: mean mutants killed, pooled over models, hybrid generator (brackets = successful runs when < 9)

| MR | dev (human) | nl (AUTO) | iauto | oauto |
|---|---|---|---|---|
| days-between | 6.0 (8) | 6.3 (8) | 6.0 | 6.0 |
| range-length | 130.0 | 139.2 | 98.5 (8) | 134.4 |
| circle-relate | 24.3 (8) | 25.2 | 22.8 | 26.4 (8) |
| longitude-normalize | 21.9 | 22.4 (8) | 20.8 | 21.7 |
| rectangle-contains | 42.9 (8) | 42.6 | 43.1 (7) | 42.9 (7) |
| renko-scaling | 10.0 | 9.4 | 9.5 (8) | 10.0 (8) |
| slope-change | 17.3 | 14.7 (6) | 15.7 (7) | 15.1 (7) |
| node-filter-clone | 13.0 | 19.4 | 12.2 | 11.0 (2) |
| cleaner-idempotence | 8.9 | 9.0 | 8.9 | 8.9 (7) |
| matrix-transpose | 16.8 (8) | 17.1 | 16.6 | 16.7 |

### RQ2: mean mutants killed per MR and model

| Model | days-between dev | days-between nl | days-between iauto | days-between oauto | range-length dev | range-length nl | range-length iauto | range-length oauto | circle-relate dev | circle-relate nl | circle-relate iauto | circle-relate oauto | longitude-normalize dev | longitude-normalize nl | longitude-normalize iauto | longitude-normalize oauto | rectangle-contains dev | rectangle-contains nl | rectangle-contains iauto | rectangle-contains oauto | renko-scaling dev | renko-scaling nl | renko-scaling iauto | renko-scaling oauto | slope-change dev | slope-change nl | slope-change iauto | slope-change oauto | node-filter-clone dev | node-filter-clone nl | node-filter-clone iauto | node-filter-clone oauto | cleaner-idempotence dev | cleaner-idempotence nl | cleaner-idempotence iauto | cleaner-idempotence oauto | matrix-transpose dev | matrix-transpose nl | matrix-transpose iauto | matrix-transpose oauto |
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
| Luna | 6.0 (2) | 7.0 (2) | 6.0 | 6.0 | 135.0 | 140.0 | 134.5 (2) | 133.7 | 19.5 (2) | 24.7 | 21.3 | 24.0 | 21.7 | 20.0 (2) | 21.3 | 21.3 | 44.0 (2) | 41.0 | 41.0 (1) | 41.0 (1) | 10.0 | 10.0 | 8.7 | 10.0 (2) | 19.0 | 15.5 (2) | 14.0 (1) | 13.0 (1) | 16.7 | 15.0 | 14.3 | n/a | 8.7 | 9.3 | 9.3 | 8.7 | 16.0 (2) | 17.3 | 16.3 | 16.0 |
| Terra | 6.0 | 6.0 | 6.0 | 6.0 | 119.3 | 132.7 | 127.0 | 135.3 | 21.3 | 23.3 | 17.3 | 23.0 (2) | 21.0 | 22.0 | 21.7 | 21.3 | 41.7 | 44.0 | 42.3 | 43.0 | 10.0 | 9.3 | 10.0 | 10.0 | 15.0 | 14.5 (2) | 16.3 | 15.0 | 11.3 | 22.0 | 11.3 | n/a | 8.7 | 9.0 | 8.7 | 9.0 | 16.7 | 17.0 | 16.3 | 16.7 |
| Sol | 6.0 | 6.0 | 6.0 | 6.0 | 135.7 | 145.0 | 46.0 | 134.3 | 30.3 | 27.7 | 29.7 | 31.0 | 23.0 | 24.3 | 19.3 | 22.3 | 43.3 | 42.7 | 44.7 | 43.3 | 10.0 | 9.0 | 10.0 (2) | 10.0 | 18.0 | 14.0 (2) | 15.7 | 16.0 | 11.0 | 21.3 | 11.0 | 11.0 (2) | 9.3 | 8.7 | 8.7 | 9.0 (1) | 17.3 | 17.0 | 17.0 | 17.3 |

### RQ2: mean ratio of kills to dev with the same model, over MRs with results

| Model | nl (AUTO) | iauto | oauto |
|---|---|---|---|
| Luna | 1.02 | 0.96 | 0.98 |
| Terra | 1.12 | 1.00 | 1.03 |
| Sol | 1.05 | 0.90 | 0.98 |
| Pooled | 1.05 | 0.94 | 0.98 |

### Mean cost and effort per successful run (list price; time = generation only)

| Configuration | Model | Success | Tokens | Cost (US$) | Time (s) | Repairs | Tests |
|---|---|---|---|---|---|---|---|
| Randoop | n/a | 30/30 | 0 | 0.00 | 9 | 0.00 | 30.8 |
| LLM, dev | Luna | 25/30 | 48442 | 0.02 | 124 | 0.84 | 30.7 |
| LLM, dev | Terra | 30/30 | 35042 | 0.20 | 142 | 0.33 | 31.9 |
| LLM, dev | Sol | 30/30 | 39818 | 0.43 | 242 | 0.17 | 46.9 |
| Hybrid, dev | Luna | 26/30 | 68087 | 0.03 | 127 | 0.69 | 32.9 |
| Hybrid, dev | Terra | 30/30 | 49304 | 0.21 | 135 | 0.23 | 27.4 |
| Hybrid, dev | Sol | 30/30 | 52606 | 0.47 | 253 | 0.13 | 47.5 |
| Hybrid, nl | Luna | 27/30 | 54612 | 0.02 | 127 | 0.52 | 31.5 |
| Hybrid, nl | Terra | 29/30 | 48091 | 0.21 | 155 | 0.21 | 33.6 |
| Hybrid, nl | Sol | 29/30 | 57692 | 0.50 | 278 | 0.28 | 47.9 |
| Hybrid, iauto | Luna | 25/30 | 56834 | 0.02 | 84 | 0.64 | 29.4 |
| Hybrid, iauto | Terra | 30/30 | 53745 | 0.24 | 132 | 0.27 | 31.5 |
| Hybrid, iauto | Sol | 29/30 | 55829 | 0.51 | 217 | 0.10 | 47.0 |
| Hybrid, oauto | Luna | 22/30 | 68782 | 0.03 | 88 | 0.82 | 29.9 |
| Hybrid, oauto | Terra | 26/30 | 55235 | 0.23 | 123 | 0.38 | 33.9 |
| Hybrid, oauto | Sol | 27/30 | 64098 | 0.55 | 228 | 0.33 | 46.6 |

All 480 runs: 26,495,761 tokens, US$110.07 at list price.
