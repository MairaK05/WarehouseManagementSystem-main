import React from "react";
import { listLogs } from "./services/AuditLogService";
import { useState, useEffect } from "react";
export default function LogDisplay() {
  const [logs, setLogs] = useState([]);
  useEffect(() => {
    listLogs()
      .then((response) => {
        setLogs(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  }, []);
  return (
    <div>
      <h2>Audit Logs</h2>
      <div>
        <table>
          <thead>
            <th>ID</th>
            <th>User Responsible</th>
            <th>Action</th>
            <th>Recipient</th>
            <th>Recipient ID</th>
            <th>Date</th>
          </thead>
          <tbody>
            {logs.map((log) => {
              const {
                logID,
                logUser,
                logAction,
                logRecipient,
                logRecipientID,
                logTimestamp,
              } = log;
              return (
                <tr key={logID}>
                  <td>{logID}</td>
                  <td>{logUser}</td>
                  <td>{logAction}</td>
                  <td>{logRecipient}</td>
                  <td>{logRecipientID}</td>
                  <td>{logTimestamp}</td>
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>
    </div>
  );
}
