import defaultSettings from "@pages/frames/settings";

const title = defaultSettings.title || "Vue Element Admin";

export default function getPageTitle(pageTitle) {
  if (pageTitle) {
    return `${pageTitle} - ${title}`;
  }
  return `${title}`;
}
