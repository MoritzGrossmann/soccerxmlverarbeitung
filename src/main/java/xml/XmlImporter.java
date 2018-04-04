package xml;

import models.*;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.DOMBuilder;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class XmlImporter {

    private File file;

    public XmlImporter(File file) {
        this.file = file;
    }

    private org.jdom2.Document getXmlDocument() throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(this.file);

        DOMBuilder domBuilder = new DOMBuilder();

        return domBuilder.build(document);
    }

    public List<Team> readTeams() {

        List<Team> teams = new ArrayList<Team>();

        try {
            Element root = getXmlDocument().getRootElement();

            Namespace namespace = root.getNamespace();

            root.getChildren("Team", namespace).forEach((team) -> {
                Namespace space = team.getNamespace();
                teams.add(
                        new Team(Integer.parseInt(team.getChildText("TeamId", space)),
                                team.getChildText("ShortName", space),
                                team.getChildText("TeamIconUrl", space),
                                team.getChildText("TeamName", space)));
            });


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return teams;
    }

    public List<Match> readMatches() {

        int i = 0;

        List<Match> matches = new ArrayList<>();

        try {

            Element root = getXmlDocument().getRootElement();

            root.getChildren("Match", root.getNamespace()).forEach((Element xmlMatch) -> {
                Match match = new Match();

                match.setId(Integer.parseInt(xmlMatch.getChildText("MatchID", xmlMatch.getNamespace())));

                List<Goal> goals = new ArrayList<>();

                Element xmlGoals = xmlMatch.getChild("Goals", xmlMatch.getNamespace());

                xmlGoals.getChildren("Goal", xmlGoals.getNamespace()).forEach(xmlGoal -> {
                    goals.add(parseGoalXml(xmlGoal, match.getId()));
                });

                match.setGoals(goals);

                List<MatchResult> matchResults = new ArrayList<>();

                Element xmlMatchresults = xmlMatch.getChild("MatchResults", xmlMatch.getNamespace());

                xmlMatchresults.getChildren("MatchResult", xmlMatchresults.getNamespace()).forEach(xmlMatchResult -> {
                    MatchResult result = parseMatchResultXml(xmlMatchResult, match.getId());
                    result.setMatchId(match.getId());
                    matchResults.add(result);
                });

                match.setMatchResults(matchResults);

                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

                java.util.Date lastUpdateDate = null;

                try {
                    lastUpdateDate = dateFormatter.parse(xmlMatch.getChildText("LastUpdateDateTime", xmlMatch.getNamespace()).replace('T', ' '));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                match.setLastUpdateTime(new Date(lastUpdateDate.getTime()));

                League league = new League(
                        Integer.parseInt(xmlMatch.getChildText("LeagueId", xmlMatch.getNamespace())),
                        xmlMatch.getChildText("LeagueName", xmlMatch.getNamespace())
                );

                match.setLeague(league);

                Element xmlLocation = xmlMatch.getChild("Location", xmlMatch.getNamespace());

                if (xmlLocation.getContent().size() > 0) {
                    Location location = new Location(
                            Integer.parseInt(xmlLocation.getChildText("LocationID", xmlLocation.getNamespace())),
                            xmlLocation.getChildText("LocationCity", xmlLocation.getNamespace()),
                            xmlLocation.getChildText("LocationStadium", xmlLocation.getNamespace())
                    );

                    match.setLocation(location);
                }

                java.util.Date matchDateTime = null;

                try {
                    matchDateTime = dateFormatter.parse(xmlMatch.getChildText("MatchDateTime", xmlMatch.getNamespace()).replace('T', ' '));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                match.setDateTime(new Date(matchDateTime.getTime()));

                try {
                    match.setDateTimeUTC(new Date(dateFormatter.parse(xmlMatch.getChildText("MatchDateTimeUTC",xmlMatch.getNamespace()).replace('T', ' ').replace('Z', ' ')).getTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                try {
                    match.setNumberOfViewers(Integer.parseInt(xmlMatch.getChildText("NumberOfViewers", xmlMatch.getNamespace())));
                } catch (NumberFormatException e) {
                    match.setNumberOfViewers(0);
                }

                match.setTimezone(xmlMatch.getChildText("TimeZoneID", xmlMatch.getNamespace()));

                Element xmlTeam1 = xmlMatch.getChild("Team1", xmlMatch.getNamespace());

                Team team1 = new Team(
                        Integer.parseInt(xmlTeam1.getChildText("TeamId", xmlTeam1.getNamespace())),
                        xmlTeam1.getChildText("ShortName", xmlTeam1.getNamespace()),
                        xmlTeam1.getChildText("TeamIconUrl", xmlTeam1.getNamespace()),
                        xmlTeam1.getChildText("TeamName", xmlTeam1.getNamespace())
                );

                match.setTeam1(team1);

                Element xmlTeam2 = xmlMatch.getChild("Team2", xmlMatch.getNamespace());

                Team team2 = new Team(
                        Integer.parseInt(xmlTeam2.getChildText("TeamId", xmlTeam2.getNamespace())),
                        xmlTeam2.getChildText("ShortName", xmlTeam2.getNamespace()),
                        xmlTeam2.getChildText("TeamIconUrl", xmlTeam2.getNamespace()),
                        xmlTeam2.getChildText("TeamName", xmlTeam2.getNamespace())
                );

                match.setTeam2(team2);

                Element xmlGroup = xmlMatch.getChild("Group", xmlMatch.getNamespace());

                match.setGroup(parseGroupXml(xmlGroup));

                match.setFinished(Boolean.parseBoolean(xmlMatch.getChildText("MatchIsFinished", xmlMatch.getNamespace())));

                matches.add(match);
            });

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return matches;
    }

    private MatchResult parseMatchResultXml(Element xmlMatchResult, int matchId) {
        MatchResult matchResult = new MatchResult();
        matchResult.setPointsTeam1(Integer.parseInt(xmlMatchResult.getChildText("PointsTeam1", xmlMatchResult.getNamespace())));
        matchResult.setPointsTeam2(Integer.parseInt(xmlMatchResult.getChildText("PointsTeam2", xmlMatchResult.getNamespace())));
        matchResult.setDescription(xmlMatchResult.getChildText("ResultDescription", xmlMatchResult.getNamespace()));
        matchResult.setId(Integer.parseInt(xmlMatchResult.getChildText("ResultID", xmlMatchResult.getNamespace())));
        matchResult.setName(xmlMatchResult.getChildText("ResultName", xmlMatchResult.getNamespace()));
        matchResult.setOrderId(Integer.parseInt(xmlMatchResult.getChildText("ResultOrderID", xmlMatchResult.getNamespace())));
        matchResult.setTypeId(Integer.parseInt(xmlMatchResult.getChildText("ResultTypeID", xmlMatchResult.getNamespace())));
        matchResult.setMatchId(matchId);
        return matchResult;
    }

    private Goal parseGoalXml(Element xmlGoal, int matchId) {
        Goal goal = new Goal();
        goal.setComment(xmlGoal.getChildText("Comment", xmlGoal.getNamespace()));
        goal.setGoalGetterId(Integer.parseInt(xmlGoal.getChildText("GoalGetterID", xmlGoal.getNamespace())));
        goal.setGoalGetterName(xmlGoal.getChildText("GoalGetterName", xmlGoal.getNamespace()));
        goal.setId(Integer.parseInt(xmlGoal.getChildText("GoalID", xmlGoal.getNamespace())));
        goal.setOverTime(Boolean.parseBoolean(xmlGoal.getChildText("IsOvertime", xmlGoal.getNamespace())));
        goal.setOwnGoal(Boolean.parseBoolean(xmlGoal.getChildText("IsOwnGoal", xmlGoal.getNamespace())));
        goal.setPenalty(Boolean.parseBoolean(xmlGoal.getChildText("IsPenalty", xmlGoal.getNamespace())));

        try {
            goal.setMinute(Integer.parseInt(xmlGoal.getChildText("MatchMinute", xmlGoal.getNamespace())));
        } catch (NumberFormatException e) {
            goal.setMinute(0);
        }

        goal.setScoreTeam1(Integer.parseInt(xmlGoal.getChildText("ScoreTeam1", xmlGoal.getNamespace())));
        goal.setScoreTeam2(Integer.parseInt(xmlGoal.getChildText("ScoreTeam2", xmlGoal.getNamespace())));
        goal.setMatchId(matchId);
        return goal;
    }

    private Group parseGroupXml(Element xmlGroup) {
        Group group = new Group();
        group.setId(Integer.parseInt(xmlGroup.getChildText("GroupID", xmlGroup.getNamespace())));
        group.setName(xmlGroup.getChildText("GroupName", xmlGroup.getNamespace()));
        group.setOrderId(Integer.parseInt(xmlGroup.getChildText("GroupOrderID", xmlGroup.getNamespace())));
        return group;
    }
}
