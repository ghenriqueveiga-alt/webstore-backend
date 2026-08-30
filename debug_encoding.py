import subprocess

result = subprocess.run(
    ['docker', 'exec', '-i', 'mysql-db', 'mysql', '-u', 'root', '-proot', 'mysql-db',
     '--default-character-set=utf8mb4', '-N', '-e',
     "SELECT CONCAT(id, '|', nome) FROM programa WHERE id = 212;"],
    capture_output=True, text=True, encoding='utf-8'
)
print('Output:', repr(result.stdout))
print('Bytes:', result.stdout.encode('utf-8').hex())

# Check episode path
result2 = subprocess.run(
    ['docker', 'exec', '-i', 'mysql-db', 'mysql', '-u', 'root', '-proot', 'mysql-db',
     '--default-character-set=utf8mb4', '-N', '-e',
     "SELECT CONCAT(e.id, '|', a.caminho) FROM episodio e JOIN arquivo a ON a.id = e.arquivo_id WHERE e.id = 8110;"],
    capture_output=True, text=True, encoding='utf-8'
)
print('\nEpisode output:', repr(result2.stdout))
print('Episode bytes:', result2.stdout.encode('utf-8').hex())
