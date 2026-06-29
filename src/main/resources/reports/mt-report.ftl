<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>${title?html}</title>
  <style>
    :root {
      color-scheme: light;
      --bg: #f8fafc;
      --panel: #ffffff;
      --text: #172033;
      --muted: #5f6b7a;
      --line: #d9e1ea;
      --pass: #0f766e;
      --pass-bg: #ccfbf1;
      --fail: #b91c1c;
      --fail-bg: #fee2e2;
      --code-bg: #111827;
      --code-text: #e5e7eb;
    }

    * {
      box-sizing: border-box;
    }

    body {
      margin: 0;
      background: var(--bg);
      color: var(--text);
      font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", sans-serif;
      line-height: 1.45;
    }

    main {
      max-width: 1180px;
      margin: 0 auto;
      padding: 32px 24px 48px;
    }

    h1, h2, h3 {
      margin: 0;
      line-height: 1.2;
    }

    h1 {
      font-size: 30px;
    }

    h2 {
      margin-top: 28px;
      font-size: 21px;
    }

    h3 {
      margin: 20px 0 10px;
      font-size: 17px;
    }

    .subtitle {
      margin: 8px 0 0;
      color: var(--muted);
    }

    .summary {
      display: grid;
      grid-template-columns: repeat(3, minmax(0, 1fr));
      gap: 12px;
      margin: 24px 0;
    }

    .metric, .panel {
      background: var(--panel);
      border: 1px solid var(--line);
      border-radius: 8px;
      padding: 16px;
    }

    .metric strong {
      display: block;
      font-size: 28px;
    }

    .metric span, dt {
      color: var(--muted);
      font-size: 13px;
      text-transform: uppercase;
      letter-spacing: 0.04em;
    }

    dl {
      display: grid;
      grid-template-columns: 190px 1fr;
      gap: 10px 16px;
      margin: 0;
    }

    dd {
      margin: 0;
      overflow-wrap: anywhere;
    }

    .badge {
      display: inline-block;
      border-radius: 999px;
      padding: 4px 10px;
      font-size: 13px;
      font-weight: 700;
    }

    .pass {
      background: var(--pass-bg);
      color: var(--pass);
    }

    .fail {
      background: var(--fail-bg);
      color: var(--fail);
    }

    .cases {
      display: grid;
      gap: 12px;
    }

    .method-list {
      margin: 0;
      padding-left: 22px;
    }

    .method-list li {
      margin: 6px 0;
      font-family: ui-monospace, SFMono-Regular, Menlo, Monaco, Consolas, "Liberation Mono", monospace;
      font-size: 14px;
    }

    .json-details {
      margin: 0 14px 14px;
      border-radius: 6px;
    }

    .table-wrap {
      overflow-x: auto;
      border: 1px solid var(--line);
      border-radius: 8px;
      background: var(--panel);
    }

    table {
      width: 100%;
      min-width: 1100px;
      border-collapse: collapse;
    }

    th, td {
      border-bottom: 1px solid var(--line);
      padding: 10px;
      text-align: left;
      vertical-align: top;
      font-size: 13px;
    }

    th {
      color: var(--muted);
      font-size: 12px;
      text-transform: uppercase;
      letter-spacing: 0.04em;
      background: #eef3f8;
    }

    tr:last-child td {
      border-bottom: 0;
    }

    .method-name {
      font-family: ui-monospace, SFMono-Regular, Menlo, Monaco, Consolas, "Liberation Mono", monospace;
      overflow-wrap: anywhere;
    }

    .cell-json {
      max-width: 260px;
      max-height: 170px;
      border-radius: 6px;
      font-size: 12px;
    }

    .table-json-details {
      border: 0;
      background: transparent;
    }

    .table-json-details summary {
      padding: 0;
      color: var(--muted);
      font-weight: 700;
    }

    details {
      background: var(--panel);
      border: 1px solid var(--line);
      border-radius: 8px;
      overflow: hidden;
    }

    summary {
      cursor: pointer;
      padding: 12px 14px;
      font-weight: 700;
    }

    pre {
      margin: 0;
      padding: 14px;
      overflow-x: auto;
      white-space: pre-wrap;
      background: var(--code-bg);
      color: var(--code-text);
      font-size: 13px;
      line-height: 1.45;
    }

    .paths {
      margin-top: 10px;
      color: var(--muted);
      font-size: 14px;
    }

    @media (max-width: 760px) {
      main {
        padding: 24px 14px 36px;
      }

      .summary {
        grid-template-columns: 1fr;
      }

      dl {
        grid-template-columns: 1fr;
      }
    }
  </style>
</head>
<body>
<main>
  <header>
    <h1>${title?html}</h1>
    <p class="subtitle">Generated at ${generatedAt?html}</p>
  </header>

  <section class="summary" aria-label="Execution summary">
    <div class="metric">
      <span>Total cases</span>
      <strong>${totalCount}</strong>
    </div>
    <div class="metric">
      <span>Passing cases</span>
      <strong>${passingCount}</strong>
    </div>
    <div class="metric">
      <span>Failing cases</span>
      <strong>${failingCount}</strong>
    </div>
  </section>

  <section class="panel">
    <h2>Run Configuration</h2>
    <dl>
      <dt>SUT</dt>
      <dd>${sutClassFile?html}</dd>
      <dt>Target method</dt>
      <dd>${targetFunction?html}</dd>
      <dt>Generated class</dt>
      <dd>${generatedClassName?html}</dd>
      <dt>MR provider</dt>
      <dd>${mrProvider?html}</dd>
      <dt>JSON required</dt>
      <dd>${jsonRequired?c}</dd>
      <dt>Test suite required</dt>
      <dd>${testSuiteRequired?c}</dd>
      <dt>Report basis</dt>
      <dd>${reportBasis?html}</dd>
      <dt>Metamorphic relation</dt>
      <dd>${metamorphicRelation?html}</dd>
      <dt>Input domain</dt>
      <dd>${inputDomain?html}</dd>
    </dl>
    <div class="paths">
      Full JSON: ${fullJsonFile?html}<br>
      Passing JSON: ${passingJsonFile?html}<br>
      Failing JSON: ${failingJsonFile?html}
    </div>
  </section>

  <#if testSuiteGenerated>
    <section>
      <h2>Generated JUnit Test Methods</h2>
      <#if caseRows?size == 0>
        <div class="panel">No executed JSON case data was available for the generated JUnit methods.</div>
      <#else>
        <div class="table-wrap">
          <table>
            <thead>
              <tr>
                <th>Case</th>
                <th>Status</th>
                <th>JUnit test method</th>
                <th>Source input</th>
                <th>Source output</th>
                <th>Follow-up input</th>
                <th>Follow-up output</th>
                <th>Expected output / behaviour</th>
                <th>Raw JSON</th>
              </tr>
            </thead>
            <tbody>
              <#list caseRows as row>
                <tr>
                  <td>${row.index}</td>
                  <td>
                    <#if row.status == "Pass">
                      <span class="badge pass">Pass</span>
                    <#else>
                      <span class="badge fail">Fail</span>
                    </#if>
                  </td>
                  <td class="method-name">${row.testMethod?html}</td>
                  <td><pre class="cell-json">${row.sourceInput?html}</pre></td>
                  <td><pre class="cell-json">${row.sourceOutput?html}</pre></td>
                  <td><pre class="cell-json">${row.followUpInput?html}</pre></td>
                  <td><pre class="cell-json">${row.followUpOutput?html}</pre></td>
                  <td><pre class="cell-json">${row.expectedOutput?html}</pre></td>
                  <td>
                    <details class="table-json-details">
                      <summary>Show JSON</summary>
                      <pre class="cell-json">${row.rawJson?html}</pre>
                    </details>
                  </td>
                </tr>
              </#list>
            </tbody>
          </table>
        </div>
      </#if>
    </section>
  </#if>

  <section>
    <h2>Failing Cases</h2>
    <div class="cases">
      <#if reportUsesTestSuite>
        <#if failingTestNames?size == 0>
          <div class="panel"><span class="badge pass">No failing tests</span></div>
        <#else>
          <details open>
            <summary><span class="badge fail">Fail</span> Generated JUnit methods (${failingTestNames?size})</summary>
            <ol class="method-list">
              <#list failingTestNames as method>
                <li>${method?html}</li>
              </#list>
            </ol>
          </details>
        </#if>
      <#elseif failingEntries?size == 0>
        <div class="panel"><span class="badge pass">No failing cases</span></div>
      <#else>
        <#list failingEntries as entry>
          <details>
            <summary><span class="badge fail">Fail</span> Case ${entry?index + 1}</summary>
            <details class="json-details">
              <summary>Show JSON data</summary>
              <pre>${entry?html}</pre>
            </details>
          </details>
        </#list>
      </#if>
    </div>
  </section>

  <section>
    <h2>Passing Cases</h2>
    <div class="cases">
      <#if reportUsesTestSuite>
        <#if passingTestNames?size == 0>
          <div class="panel"><span class="badge fail">No passing tests</span></div>
        <#else>
          <details open>
            <summary><span class="badge pass">Pass</span> Generated JUnit methods (${passingTestNames?size})</summary>
            <ol class="method-list">
              <#list passingTestNames as method>
                <li>${method?html}</li>
              </#list>
            </ol>
          </details>
        </#if>
      <#elseif passingEntries?size == 0>
        <div class="panel"><span class="badge fail">No passing cases</span></div>
      <#else>
        <#list passingEntries as entry>
          <details>
            <summary><span class="badge pass">Pass</span> Case ${entry?index + 1}</summary>
            <details class="json-details">
              <summary>Show JSON data</summary>
              <pre>${entry?html}</pre>
            </details>
          </details>
        </#list>
      </#if>
    </div>
  </section>
</main>
</body>
</html>
